package ua.citizen.messagedrivenmicroservices.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ua.citizen.messagedrivenmicroservices.orderservice.config"
})
public class OrderProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }
 }