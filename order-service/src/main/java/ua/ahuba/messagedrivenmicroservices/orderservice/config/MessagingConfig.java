package ua.ahuba.messagedrivenmicroservices.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ahuba.messagedrivenmicroservices.orderservice.messaging.OrderProcessor;
import ua.ahuba.messagedrivenmicroservices.orderservice.messaging.OrderProcessorImpl;
import ua.ahuba.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.ahuba.messaging.Order;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public OrderProcessor orderProcessor(OrderService service, ObjectMapper mapper) {
        return new OrderProcessorImpl(mapper, service);
    }

    @Bean
    public Consumer<Order> orderConsumer(OrderProcessor orderProcessor, ObjectMapper mapper) {
        return order -> {
            try {
                log.info("\nOrder received:" + System.lineSeparator() + mapper.writeValueAsString(order));
                orderProcessor.process(order);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        };
    }
}
