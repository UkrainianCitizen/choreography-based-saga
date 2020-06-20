package ua.citizen.messagedrivenmicroservices.orderservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ua.citizen.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;

@Service
@Slf4j
@AllArgsConstructor
public class OrderProcessorImpl implements OrderProcessor {

    private final ObjectMapper mapper;
    private final OrderService service;

    @Override
    public Order process(Order order) throws Exception {
        val confirmedOrder = service.findById(order.getId());

        if (confirmedOrder.getStatus() != OrderStatus.REJECTED) {
            confirmedOrder.setStatus(order.getStatus());
            service.update(confirmedOrder);
            log.info("Order status updated:" + System.lineSeparator() + mapper.writeValueAsString(order));
        }
        return confirmedOrder;
    }
}