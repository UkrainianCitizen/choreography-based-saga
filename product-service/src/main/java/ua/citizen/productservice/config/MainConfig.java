package ua.citizen.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.citizen.productservice.repository.ProductRepository;
import ua.citizen.productservice.repository.ProductRepositoryImpl;
import ua.citizen.productservice.service.ProductService;
import ua.citizen.productservice.service.ProductServiceImpl;

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