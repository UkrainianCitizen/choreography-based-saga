package ua.ahuba.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.ahuba.productservice.model.Product;
import ua.ahuba.productservice.repository.ProductRepository;

@SpringBootApplication(scanBasePackages = {
		"ua.ahuba.productservice.config"
})
@Slf4j
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(ProductRepository repo, ObjectMapper mapper) {
		return args -> {
			repo.add(new Product("Test1", 1000, 20));
			repo.add(new Product("Test2", 1500, 10));
			repo.add(new Product("Test3", 2000, 20));
			repo.add(new Product("Test4", 3000, 20));
			repo.add(new Product("Test5", 1300, 10));
			repo.add(new Product("Test6", 2700, 10));
			repo.add(new Product("Test7", 3500, 10));
			repo.add(new Product("Test8", 1250, 10));
			repo.add(new Product("Test9", 2450, 10));
			repo.add(new Product("Test10", 800, 10));
			log.info("Preloading data:\n" + mapper.writeValueAsString(repo.findAll()));
		};
	}
}