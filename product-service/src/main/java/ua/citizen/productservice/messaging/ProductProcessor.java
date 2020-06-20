package ua.citizen.productservice.messaging;

import ua.citizen.messagedrivenmicroservices.messaging.Order;

public interface ProductProcessor {
    Order process(Order order) throws Exception;
}
