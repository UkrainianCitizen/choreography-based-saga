package ua.ahuba.messagedrivenmicroservices.orderservice.messaging;

import ua.ahuba.messaging.Order;

public interface OrderProcessor {

    Order process(final Order order) throws Exception;
}