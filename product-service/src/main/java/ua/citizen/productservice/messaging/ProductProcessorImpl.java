package ua.citizen.productservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ua.citizen.messagedrivenmicroservices.messaging.Order;
import ua.citizen.messagedrivenmicroservices.messaging.OrderStatus;
import ua.citizen.productservice.service.ProductService;

@Slf4j
@AllArgsConstructor
public class ProductProcessorImpl implements ProductProcessor {

    private final ProductService service;
    private final ObjectMapper mapper;

    @Override
    public Order process(final Order order) throws Exception {
        for (val productId : order.getProductIds()) {
            val product = service.findById(productId);
            log.info("\nProduct was found: {}", mapper.writeValueAsString(product));
            if (product.getCount() == 0) {
                order.setStatus(OrderStatus.REJECTED);
                break;
            }
            product.setCount(product.getCount() - 1);
            service.update(product);
            log.info("\nProduct updated: {}", mapper.writeValueAsString(product));
        }
        if (order.getStatus() != OrderStatus.REJECTED) {
            order.setStatus(OrderStatus.ACCEPTED);
        }
        return order;
    }
}
