package ua.citizen.productservice.repository;

import ua.citizen.productservice.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product add(Product product) {
        product.setId((long) (products.size()+1));
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        products.set(product.getId().intValue() - 1, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        products.remove(id.intValue());
    }

    @Override
    public List<Product> find(List<Long> ids) {
        return products.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}