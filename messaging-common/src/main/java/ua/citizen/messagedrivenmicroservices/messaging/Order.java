package ua.citizen.messagedrivenmicroservices.messaging;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private OrderStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long accountId;

    private Long customerId;

    private List<Long> productIds;
}