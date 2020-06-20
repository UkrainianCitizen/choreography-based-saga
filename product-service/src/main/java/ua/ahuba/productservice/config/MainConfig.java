package ua.ahuba.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ahuba.productservice.repository.ProductRepository;
import ua.ahuba.productservice.repository.ProductRepositoryImpl;
import ua.ahuba.productservice.service.ProductService;
import ua.ahuba.productservice.service.ProductServiceImpl;

@Configuration
public class MainConfig {

    @Bean
    public ProductService service() {
        return new ProductServiceImpl(repository());
    }

    @Bean
    public ProductRepository repository() {
        return new ProductRepositoryImpl();
    }
}