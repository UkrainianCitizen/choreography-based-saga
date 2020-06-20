package ua.citizen.messagedrivenmicroservices.orderservice.config;

import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepositoryImpl;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderServiceImpl;

@Configuration
public class MainConfig {

    @PollableBean
    public OrderService service() {
        return new OrderServiceImpl(repository());
    }

    @Bean
    public OrderRepository repository() {
        return new OrderRepositoryImpl();
    }
}