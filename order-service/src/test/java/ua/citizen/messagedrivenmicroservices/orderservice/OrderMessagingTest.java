package ua.citizen.messagedrivenmicroservices.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = OrderProcessorApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(TestChannelBinderConfiguration.class)
public class OrderMessagingTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InputDestination source;

    @Autowired
    private OutputDestination target;

    @Test
    public void orderRoutersTest() throws IOException {
        supplierTest();
    }

    private void supplierTest() throws IOException {
        // when
        restTemplate.postForObject("http://localhost:8090", preparePostOrder(), Void.class);

        //then
        Message<byte[]> received = target.receive(0, "orders-out");

        assertNotNull(received.getPayload());
        assertTrue(isSupplierOutputCorrect(received));
    }

    private Order preparePostOrder() {
        Order order = new Order();

        order.setCustomerId(4L);
        order.setStatus(OrderStatus.NEW);

        List<Long> productIds = new ArrayList<>();
        productIds.add(4L);
        productIds.add(7L);
        productIds.add(10L);
        order.setProductIds(productIds);
        return order;
    }

    private boolean isSupplierOutputCorrect(Message<byte[]> received) throws IOException {
        Order result = (objectMapper.readValue(received.getPayload(), Order.class));

        return result.getStatus().equals(OrderStatus.NEW) && result.getCustomerId() == 4L
                && result.getProductIds().contains(4L) &&
                result.getProductIds().contains(7L) &&
                result.getProductIds().contains(10L);
    }
}