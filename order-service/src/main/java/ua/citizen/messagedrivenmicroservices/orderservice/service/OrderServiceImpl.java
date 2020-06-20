package ua.citizen.messagedrivenmicroservices.orderservice.service;

import lombok.AllArgsConstructor;
import ua.citizen.messagedrivenmicroservices.orderservice.repository.OrderRepository;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import java.util.List;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order add(Order order) {
        return orderRepository.add(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }

    @Override
    public List<Order> find(List<Long> ids) {
        return orderRepository.find(ids);
    }

    @Override
    public int countByCustomerId(Long customerId) {
        return 0;
    }
}