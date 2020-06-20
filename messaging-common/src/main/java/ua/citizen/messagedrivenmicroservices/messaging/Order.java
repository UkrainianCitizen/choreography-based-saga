package ua.citizen.messagedrivenmicroservices.messaging;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private Long id;
    private OrderStatus status;
    private int price;
    private Long customerId;
    private Long accountId;
    private List<Long> productIds;
}