package ua.ahuba.messagedrivenmicroservices.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.ahuba.messaging.Order;

public interface AccountProcessor {
    Order process(final Order order) throws JsonProcessingException;
}
