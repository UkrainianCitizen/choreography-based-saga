package ua.ahuba.productservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ahuba.productservice.model.Product;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;
import ua.ahuba.productservice.service.ProductService;

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
