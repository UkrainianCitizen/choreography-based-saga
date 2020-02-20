package ua.ahuba.productservice.repository;

import ua.ahuba.productservice.model.Product;
import java.util.List;

public interface ProductRepository {
    Product add(Product product);

    Product update(Product product);

    Product findById(Long id);

    void delete(Long id);

    List<Product> find(List<Long> ids);

    List<Product> findAll();
}
