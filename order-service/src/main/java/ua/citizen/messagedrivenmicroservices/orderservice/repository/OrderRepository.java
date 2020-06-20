package ua.citizen.messagedrivenmicroservices.orderservice.repository;

import ua.citizen.messagedrivenmicroservices.messaging.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order add(Order order);

    Order update(Order order);

    Optional<Order> findById(Long id);

    void delete(Long id);

    List<Order> find(List<Long> ids);

    int countByCustomerId(Long customerId);
}