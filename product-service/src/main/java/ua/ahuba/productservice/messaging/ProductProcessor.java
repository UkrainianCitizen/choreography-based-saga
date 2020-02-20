package ua.ahuba.productservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.ahuba.messaging.Order;

public interface ProductProcessor {
    Order process(Order order) throws JsonProcessingException;
}
