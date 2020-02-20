package ua.ahuba.messagedrivenmicroservices.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import ua.ahuba.messagedrivenmicroservices.orderservice.messaging.OrderProcessor;
import ua.ahuba.messagedrivenmicroservices.orderservice.messaging.OrderProcessorImpl;
import ua.ahuba.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import ua.ahuba.messagedrivenmicroservices.orderservice.repository.OrderRepositoryImpl;
import ua.ahuba.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.ahuba.messagedrivenmicroservices.orderservice.service.OrderServiceImpl;
import ua.ahuba.messaging.Order;
import java.util.function.Consumer;

@SpringBootApplication
public class OrderProcessorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }

    @Bean
    public ObjectWriter jsonWriter() {
        return new ObjectMapper().writerWithDefaultPrettyPrinter();
    }

    @Bean
    public Consumer<Order> orderConsumer() {
        return order -> {
            try {
                LOGGER.info("\nOrder received:" + System.lineSeparator() + jsonWriter().writeValueAsString(order));
                orderProcessor().process(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @Bean
    public OrderProcessor orderProcessor() {
        return new OrderProcessorImpl(service(), jsonWriter());
    }

    @PollableBean
    public OrderService service() {
        return new OrderServiceImpl(repository());
    }

    @Bean
    public OrderRepository repository() {
        return new OrderRepositoryImpl();
    }
}