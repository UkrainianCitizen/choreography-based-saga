package ua.citizen.messagedrivenmicroservices.accountservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.citizen.messagedrivenmicroservices.accountservice.repo.AccountRepository;
import ua.citizen.messagedrivenmicroservices.accountservice.repo.AccountRepositoryImpl;
import ua.citizen.messagedrivenmicroservices.accountservice.service.AccountService;
import ua.citizen.messagedrivenmicroservices.accountservice.service.AccountServiceImpl;

@Configuration
public class MainConfig {

    @Bean
    public AccountService service() {
        return new AccountServiceImpl(repository());
    }

    @Bean
    public AccountRepository repository() {
        return new AccountRepositoryImpl();
    }
}