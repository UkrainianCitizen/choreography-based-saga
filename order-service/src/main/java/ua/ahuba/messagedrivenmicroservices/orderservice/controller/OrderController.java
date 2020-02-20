package ua.ahuba.messagedrivenmicroservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import ua.ahuba.messaging.Order;
import ua.ahuba.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import java.util.function.Supplier;

@RestController
public final class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private ObjectMapper mapper;

    private final OrderRepository repo;
    private final EmitterProcessor<Order> processor;

    public OrderController(ObjectMapper mapper, OrderRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
        this.processor = EmitterProcessor.create();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void produceOrder(@RequestBody Order order) throws Exception {
        LOGGER.info("\nOrder sent: {}", mapper.writeValueAsString(repo.add(order)));
        processor.onNext(order);
    }

    @Bean
    public Supplier<Flux<Order>> orderSupplier() {
        return () -> processor;
    }
}