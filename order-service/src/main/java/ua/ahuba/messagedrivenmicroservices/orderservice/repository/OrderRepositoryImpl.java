package ua.ahuba.messagedrivenmicroservices.orderservice.repository;

import ua.ahuba.messaging.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order add(Order order) {
        order.setId((long) (orders.size() + 1));
        orders.add(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        orders.set(order.getId().intValue() - 1, order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        orders.remove(id.intValue());
    }

    @Override
    public List<Order> find(List<Long> ids) {
        return orders.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }

    @Override
    public int countByCustomerId(Long customerId) {
        return (int) orders.stream().filter(order -> order.getCustomerId().equals(customerId)).count();
    }
}