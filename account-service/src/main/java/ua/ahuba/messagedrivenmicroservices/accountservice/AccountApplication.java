package ua.ahuba.messagedrivenmicroservices.accountservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.ahuba.messagedrivenmicroservices.accountservice.messaging.AccountProcessor;
import ua.ahuba.messagedrivenmicroservices.accountservice.messaging.AccountProcessorImpl;
import ua.ahuba.messagedrivenmicroservices.accountservice.model.Account;
import ua.ahuba.messagedrivenmicroservices.accountservice.repo.AccountRepository;
import ua.ahuba.messagedrivenmicroservices.accountservice.repo.AccountRepositoryImpl;
import ua.ahuba.messagedrivenmicroservices.accountservice.service.AccountService;
import ua.ahuba.messagedrivenmicroservices.accountservice.service.AccountServiceImpl;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;
import java.util.Collections;
import java.util.function.Function;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class AccountApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @PostConstruct
    public void startupLog() throws JsonProcessingException {
        LOGGER.info("\n" + jsonWriter().writeValueAsString(repository().findAll()));
    }

    @Bean
    public ObjectWriter jsonWriter() {
        return new ObjectMapper().writerWithDefaultPrettyPrinter();
    }

    @Bean
    public AccountProcessor accountProcessor() {
        return new AccountProcessorImpl(service());
    }

    @Bean
    public Function<Order, Order> confirmedOrderByAccount() {
        return order -> {
            Order processedOrder = new Order();
            try {
                LOGGER.info("\nOrder received:" + System.lineSeparator() + jsonWriter().writeValueAsString(order));
                processedOrder = accountProcessor().process(order);
                LOGGER.info("\nOrder status:" + System.lineSeparator() + jsonWriter().writeValueAsString(Collections.singletonMap("status", order.getStatus())));
                LOGGER.info("\nOrder response sent:" + System.lineSeparator() + jsonWriter().writeValueAsString(order));
            } catch (JsonProcessingException e) {
                processedOrder.setStatus(OrderStatus.REJECTED);
                e.printStackTrace();
            }
            return processedOrder;
        };
    }

    @Bean
    public AccountService service() {
        return new AccountServiceImpl(repository());
    }

    @Bean
    public AccountRepository repository() {
        AccountRepository repository = new AccountRepositoryImpl();
        repository.add(new Account("1234567890", 50000, 1L));
        repository.add(new Account("1234567891", 50000, 1L));
        repository.add(new Account("1234567892", 0, 1L));
        repository.add(new Account("1234567893", 50000, 2L));
        repository.add(new Account("1234567894", 0, 2L));
        repository.add(new Account("1234567895", 50000, 2L));
        repository.add(new Account("1234567896", 0, 3L));
        repository.add(new Account("1234567897", 50000, 3L));
        repository.add(new Account("1234567898", 50000, 3L));
        return repository;
    }

}