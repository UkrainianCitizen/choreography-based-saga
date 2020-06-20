package ua.citizen.messagedrivenmicroservices.accountservice.repo;

import ua.citizen.messagedrivenmicroservices.accountservice.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account add(Account account);

    Account update(Account account);

    void delete(Long id);

    List<Account> find(List<Long> ids);

    Optional<Account> findById(Long customerId);

    List<Account> findAll();
}
