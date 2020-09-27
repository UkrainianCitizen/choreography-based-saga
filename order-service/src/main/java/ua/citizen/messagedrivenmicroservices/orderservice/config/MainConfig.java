package ua.citizen.messagedrivenmicroservices.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.orderservice.controller.OrderController;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepositoryImpl;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderServiceImpl;

@Configuration
public class MainConfig {

    @Bean
    public OrderController controller(OrderService service, ObjectMapper mapper, EmitterProcessor<Order> emitterProcessor) {
        return new OrderController(service, mapper, emitterProcessor);
    }

    @Bean
    public OrderService service() {
        return new OrderServiceImpl(repository());
    }

    @Bean
    public OrderRepository repository() {
        return new OrderRepositoryImpl();
    }
}