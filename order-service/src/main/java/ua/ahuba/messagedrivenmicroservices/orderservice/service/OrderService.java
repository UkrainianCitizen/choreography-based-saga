package ua.ahuba.messagedrivenmicroservices.orderservice.service;

import ua.ahuba.messaging.Order;
import java.util.List;

public interface OrderService {

    Order add(Order order);

    Order update(Order order);

    Order findById(Long id);

    void delete(Long id);

    List<Order> find(List<Long> ids);

    int countByCustomerId(Long customerId);
}