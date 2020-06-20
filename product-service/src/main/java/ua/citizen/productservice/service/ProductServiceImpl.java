package ua.citizen.productservice.service;

import ua.citizen.productservice.model.Product;
import ua.citizen.productservice.repository.ProductRepository;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public Product findById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("No products was found."));
    }

    @Override
    public List<Product> find(List<Long> ids) {
        return productRepository.find(ids);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
