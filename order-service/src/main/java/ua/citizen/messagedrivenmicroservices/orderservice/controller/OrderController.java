package ua.citizen.messagedrivenmicroservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.tomcat.jni.OS;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;
import java.util.function.Supplier;

@RestController
@Slf4j
@AllArgsConstructor
public final class OrderController {

    private final OrderService service;
    private final ObjectMapper mapper;
    private final EmitterProcessor<Order> emitterProcessor;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void produceOrder(@RequestBody Order order) throws Exception {
        log.info("\nOrder sent: {}", mapper.writeValueAsString(service.add(order)));
        emitterProcessor.onNext(order);
    }
}