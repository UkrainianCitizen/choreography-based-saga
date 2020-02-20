package ua.ahuba.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;
import ua.ahuba.productservice.messaging.ProductProcessor;
import ua.ahuba.productservice.messaging.ProductProcessorImpl;
import ua.ahuba.productservice.model.Product;
import ua.ahuba.productservice.repository.ProductRepository;
import ua.ahuba.productservice.repository.ProductRepositoryImpl;
import ua.ahuba.productservice.service.ProductService;
import ua.ahuba.productservice.service.ProductServiceImpl;
import java.util.Collections;
import java.util.function.Function;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProductApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@PostConstruct
	public void startupLog() throws JsonProcessingException {
		LOGGER.info("\n" + jsonWriter().writeValueAsString(productRepository().findAll()));
	}

	@Bean
	public ObjectWriter jsonWriter() {
		return new ObjectMapper().writerWithDefaultPrettyPrinter();
	}

	@Bean
	public Function<Order, Order> confirmedOrderByProduct() {
		return order -> {
			Order processedOrder = new Order();
			try {
				LOGGER.info("\nOrder received: {}", jsonWriter().writeValueAsString(order));
				processedOrder = productProcessor().process(order);
				LOGGER.info("\nOrder status: {}", jsonWriter().writeValueAsString(order));
				LOGGER.info("\nOrder response sent: {}", jsonWriter().writeValueAsString(Collections.singletonMap("status", order.getStatus())));
			} catch (JsonProcessingException e) {
				processedOrder.setStatus(OrderStatus.REJECTED);
				e.printStackTrace();
			}
			return processedOrder;
		};
	}

	@Bean
	public ProductProcessor productProcessor() {
		return new ProductProcessorImpl(productRepository(), jsonWriter());
	}

	@Bean
	public ProductService productService() {
		return new ProductServiceImpl(productRepository());
	}

	@Bean
	public ProductRepository productRepository() {
		ProductRepository repository = new ProductRepositoryImpl();
		repository.add(new Product("Test1", 1000, 20));
		repository.add(new Product("Test2", 1500, 10));
		repository.add(new Product("Test3", 2000, 20));
		repository.add(new Product("Test4", 3000, 20));
		repository.add(new Product("Test5", 1300, 10));
		repository.add(new Product("Test6", 2700, 10));
		repository.add(new Product("Test7", 3500, 10));
		repository.add(new Product("Test8", 1250, 10));
		repository.add(new Product("Test9", 2450, 10));
		repository.add(new Product("Test10", 800, 10));
		return repository;
	}
}