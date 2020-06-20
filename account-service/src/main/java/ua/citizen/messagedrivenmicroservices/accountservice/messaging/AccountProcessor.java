package ua.citizen.messagedrivenmicroservices.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.citizen.messagedrivenmicroservices.messaging.Order;

public interface AccountProcessor {
    Order process(final Order order) throws JsonProcessingException;
}
