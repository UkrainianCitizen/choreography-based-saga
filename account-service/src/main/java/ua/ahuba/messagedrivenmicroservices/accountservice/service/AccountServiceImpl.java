package ua.ahuba.messagedrivenmicroservices.accountservice.service;

import ua.ahuba.messagedrivenmicroservices.accountservice.model.Account;
import ua.ahuba.messagedrivenmicroservices.accountservice.repo.AccountRepository;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account add(Account account) {
        return accountRepository.add(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.update(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }

    @Override
    public List<Account> find(List<Long> ids) {
        return accountRepository.find(ids);
    }

    @Override
    public Account findById(Long customerId) {
        return accountRepository.findById(customerId).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}