package ua.citizen.messagedrivenmicroservices.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import ua.citizen.messagedrivenmicroservices.accountservice.AccountApplication;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {AccountApplication.class})
@Import(TestChannelBinderConfiguration.class)
public class AccountControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InputDestination source;

    @Autowired
    private OutputDestination target;

    @Test
    public void functionTest() throws IOException {

        source.send(new GenericMessage<>(prepareInputOrder()));

        Message<byte[]> received = target.receive();

        assertNotNull(received.getPayload());
        assertTrue(isOutputCorrect(received));
    }

    private boolean isOutputCorrect(Message<byte[]> received) throws IOException {
        Order result = (objectMapper.readValue(received.getPayload(), Order.class));

        return result.getStatus().equals(OrderStatus.ACCEPTED);
    }

    private Order prepareInputOrder() {
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setCustomerId(1L);
        return order;
    }
}