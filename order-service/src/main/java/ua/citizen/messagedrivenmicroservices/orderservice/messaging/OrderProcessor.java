package ua.citizen.messagedrivenmicroservices.orderservice.messaging;

import ua.citizen.messagedrivenmicroservices.messaging.Order;

public interface OrderProcessor {

    Order process(final Order order) throws Exception;
}