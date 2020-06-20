package ua.citizen.messagedrivenmicroservices.accountservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.citizen.messagedrivenmicroservices.accountservice.messaging.AccountProcessor;
import ua.citizen.messagedrivenmicroservices.accountservice.messaging.AccountProcessorImpl;
import ua.citizen.messagedrivenmicroservices.accountservice.service.AccountService;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;
import java.util.Collections;
import java.util.function.Function;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public AccountProcessor accountProcessor(ObjectMapper mapper, AccountService service) {
        return new AccountProcessorImpl(mapper, service);
    }

    @Bean
    public Function<Order, Order> confirmedOrderByAccount(ObjectMapper mapper, AccountProcessor processor) {
        return order -> {
            var processedOrder = new Order();
            try {
                log.info("\nOrder received:" + System.lineSeparator() + mapper.writeValueAsString(order));
                processedOrder = processor.process(order);
                log.info("\nOrder status:" + System.lineSeparator() + mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
                log.info("\nOrder response sent:" + System.lineSeparator() + mapper.writeValueAsString(order));
            } catch (JsonProcessingException e) {
                processedOrder.setStatus(OrderStatus.REJECTED);
                e.printStackTrace();
            }
            return processedOrder;
        };
    }
}
