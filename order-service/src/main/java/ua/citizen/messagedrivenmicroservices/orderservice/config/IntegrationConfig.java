package ua.citizen.messagedrivenmicroservices.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.orderservice.messaging.OrderProcessor;
import ua.citizen.messagedrivenmicroservices.orderservice.messaging.OrderProcessorImpl;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;

@Configuration
public class IntegrationConfig {

    @Bean
    public EmitterProcessor<Order> emitterProcessor() {
        return EmitterProcessor.create();
    }

    @Bean
    public OrderProcessor orderProcessor(OrderService service, ObjectMapper mapper) {
        return new OrderProcessorImpl(mapper, service);
    }
}
