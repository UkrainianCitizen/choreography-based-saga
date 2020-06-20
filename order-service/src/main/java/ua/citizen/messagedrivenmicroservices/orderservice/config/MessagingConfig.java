package ua.citizen.messagedrivenmicroservices.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import ua.citizen.messagedrivenmicroservices.orderservice.messaging.OrderProcessor;
import ua.citizen.messagedrivenmicroservices.orderservice.messaging.OrderProcessorImpl;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public OrderProcessor orderProcessor(OrderService service, ObjectMapper mapper) {
        return new OrderProcessorImpl(mapper, service);
    }

    @Bean
    public EmitterProcessor<Order> emitterProcessor() {
        return EmitterProcessor.create();
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

    @Bean
    public Supplier<Flux<Order>> orderSupplier() {
        return this::emitterProcessor;
    }
}
