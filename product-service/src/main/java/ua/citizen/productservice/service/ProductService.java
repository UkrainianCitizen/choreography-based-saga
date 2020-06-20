package ua.citizen.productservice.service;

import ua.citizen.productservice.model.Product;
import java.util.List;

public interface ProductService {

    Product add(Product product);

    Product update(Product product);

    Product findById(Long id) throws Exception;

    void delete(Long id);

    List<Product> find(List<Long> ids);

    List<Product> findAll();
}
