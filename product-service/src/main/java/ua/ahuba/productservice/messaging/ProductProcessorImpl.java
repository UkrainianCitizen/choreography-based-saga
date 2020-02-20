package ua.ahuba.productservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ahuba.productservice.model.Product;
import ua.ahuba.productservice.repository.ProductRepository;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;

public class ProductProcessorImpl implements ProductProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProcessorImpl.class);

    private final ProductRepository repository;
    private final ObjectWriter objectWriter;

    public ProductProcessorImpl(ProductRepository repository, ObjectWriter objectWriter) {
        this.repository = repository;
        this.objectWriter = objectWriter;
    }

    @Override
    public Order process(final Order order) throws JsonProcessingException {
        for (Long productId : order.getProductIds()) {
            Product product = repository.findById(productId);
            LOGGER.info("\nProduct was found: {}", objectWriter.writeValueAsString(product));
            if (product.getCount() == 0) {
                order.setStatus(OrderStatus.REJECTED);
                break;
            }
            product.setCount(product.getCount() - 1);
            repository.update(product);
            LOGGER.info("\nProduct updated: {}", objectWriter.writeValueAsString(product));
        }
        if (order.getStatus() != OrderStatus.REJECTED) {
            order.setStatus(OrderStatus.ACCEPTED);
        }
        return order;
    }
}
