package ua.ahuba.productservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;
import ua.ahuba.productservice.messaging.ProductProcessor;
import ua.ahuba.productservice.messaging.ProductProcessorImpl;
import ua.ahuba.productservice.service.ProductService;
import java.util.Collections;
import java.util.function.Function;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public ProductProcessor productProcessor(ProductService service, ObjectMapper mapper) {
        return new ProductProcessorImpl(service, mapper);
    }

    @Bean
    public Function<Order, Order> confirmedOrderByProduct(ProductProcessor processor, ObjectMapper mapper) {
        return order -> {
            var processedOrder = new Order();
            try {
                log.info("\nOrder received: {}", mapper.writeValueAsString(order));
                processedOrder = processor.process(order);
                log.info("\nOrder status: {}", mapper.writeValueAsString(order));
                log.info("\nOrder response sent: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
            } catch (Exception e) {
                processedOrder.setStatus(OrderStatus.REJECTED);
                log.error(e.getMessage(), e);
            }
            return processedOrder;
        };
    }
}
