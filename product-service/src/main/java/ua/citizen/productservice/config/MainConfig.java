package ua.citizen.productservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.citizen.productservice.controller.ProductController;
import ua.citizen.productservice.repository.ProductRepository;
import ua.citizen.productservice.repository.ProductRepositoryImpl;
import ua.citizen.productservice.service.ProductService;
import ua.citizen.productservice.service.ProductServiceImpl;

@Configuration
public class MainConfig {

    @Bean
    public ProductController controller(ObjectMapper mapper){
        return new ProductController(service(), mapper);
    }

    @Bean
    public ProductService service() {
        return new ProductServiceImpl(repository());
    }

    @Bean
    public ProductRepository repository() {
        return new ProductRepositoryImpl();
    }
}