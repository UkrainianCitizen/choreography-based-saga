package ua.citizen.messagedrivenmicroservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;

@Slf4j
@RestController
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