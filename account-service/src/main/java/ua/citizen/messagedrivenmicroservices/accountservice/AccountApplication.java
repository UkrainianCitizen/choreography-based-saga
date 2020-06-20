package ua.citizen.messagedrivenmicroservices.accountservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.citizen.messagedrivenmicroservices.accountservice.model.Account;
import ua.citizen.messagedrivenmicroservices.accountservice.repo.AccountRepository;

@SpringBootApplication(scanBasePackages = {
        "ua.citizen.messagedrivenmicroservices.accountservice.config"
})
@Slf4j
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(AccountRepository repo, ObjectMapper mapper) {
        return args -> {
            repo.add(new Account("1234567890", 50000, 1L));
            repo.add(new Account("1234567891", 50000, 1L));
            repo.add(new Account("1234567892", 0, 1L));
            repo.add(new Account("1234567893", 50000, 2L));
            repo.add(new Account("1234567894", 0, 2L));
            repo.add(new Account("1234567895", 50000, 2L));
            repo.add(new Account("1234567896", 0, 3L));
            repo.add(new Account("1234567897", 50000, 3L));
            repo.add(new Account("1234567898", 50000, 3L));
            log.info("Preloading data:\n" + mapper.writeValueAsString(repo.findAll()));
        };
    }
}