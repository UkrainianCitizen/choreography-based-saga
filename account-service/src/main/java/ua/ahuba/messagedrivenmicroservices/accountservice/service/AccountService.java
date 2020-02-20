package ua.ahuba.messagedrivenmicroservices.accountservice.service;

import ua.ahuba.messagedrivenmicroservices.accountservice.model.Account;
import java.util.List;

public interface AccountService {

    Account add(Account account);

    Account update(Account account);

    void delete(Long id);

    List<Account> find(List<Long> ids);

    Account findById(Long customerId);

    List<Account> findAll();
}