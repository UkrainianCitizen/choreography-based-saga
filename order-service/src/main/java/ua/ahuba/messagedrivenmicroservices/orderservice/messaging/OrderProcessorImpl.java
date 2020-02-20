package ua.ahuba.messagedrivenmicroservices.orderservice.messaging;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.ahuba.messagedrivenmicroservices.orderservice.service.OrderService;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;

@Service
public class OrderProcessorImpl implements OrderProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessorImpl.class);

    private ObjectWriter writer;

    private final OrderService orderService;

    public OrderProcessorImpl(OrderService orderService, ObjectWriter writer) {
        this.orderService = orderService;
        this.writer = writer;
    }

    @Override
    public Order process(Order order) throws Exception {
        Order confirmedOrder = orderService.findById(order.getId());

        if (confirmedOrder.getStatus() != OrderStatus.REJECTED) {
            confirmedOrder.setStatus(order.getStatus());
            orderService.update(confirmedOrder);
            LOGGER.info("Order status updated:" + System.lineSeparator() + writer.writeValueAsString(order));
        }
        return confirmedOrder;
    }

}
