package ua.citizen.productservice.repository;

import ua.citizen.productservice.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product add(Product product);

    Product update(Product product);

    Optional<Product> findById(Long id);

    void delete(Long id);

    List<Product> find(List<Long> ids);

    List<Product> findAll();
}
