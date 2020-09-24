package ua.citizen.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;
import ua.citizen.productservice.config.MessagingConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductMessagingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void functionTest() throws IOException {

        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration.getCompleteConfiguration(
                        SampleConfiguration.class))
                .run("--spring.cloud.function.definition=confirmedOrderByProduct")) {

            InputDestination source = context.getBean(InputDestination.class);
            OutputDestination target = context.getBean(OutputDestination.class);

            source.send(new GenericMessage<>(prepareInputOrder()));

            Message<byte[]> received = target.receive();

            assertNotNull(received.getPayload());
            assertTrue(isOutputCorrect(received));
        }
    }

    private boolean isOutputCorrect(Message<byte[]> received) throws IOException {
        Order result = (objectMapper.readValue(received.getPayload(), Order.class));

        return result.getStatus().equals(OrderStatus.ACCEPTED);
    }

    private Order prepareInputOrder() {
        Order order = new Order();

        order.setStatus(OrderStatus.NEW);

        List<Long> productIds = new ArrayList<>();
        productIds.add(4L);
        productIds.add(7L);
        productIds.add(10L);
        order.setProductIds(productIds);
        return order;
    }

    @SpringBootApplication
    @Import(MessagingConfig.class)
    public static class SampleConfiguration {

    }
}