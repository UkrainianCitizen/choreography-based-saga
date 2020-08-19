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
            repo.add(new Account(1L, 50000));
            repo.add(new Account(1L, 50000));
            repo.add(new Account(1L, 0));
            repo.add(new Account(2L, 50000));
            repo.add(new Account(2L, 0));
            repo.add(new Account(2L, 50000));
            repo.add(new Account(3L, 0));
            repo.add(new Account(3L, 50000));
            repo.add(new Account(3L, 50000));
            log.info("Preloading data:\n" + mapper.writeValueAsString(repo.findAll()));
        };
    }
}