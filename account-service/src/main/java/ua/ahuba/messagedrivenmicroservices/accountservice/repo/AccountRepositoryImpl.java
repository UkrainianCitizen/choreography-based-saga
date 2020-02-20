package ua.ahuba.messagedrivenmicroservices.accountservice.repo;

import ua.ahuba.messagedrivenmicroservices.accountservice.model.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account add(Account account) {
        account.setId((long) (accounts.size() + 1));
        accounts.add(account);
        return account;
    }

    @Override
    public Account update(Account account) {
        accounts.set(account.getId().intValue() - 1, account);
        return account;
    }

    @Override
    public void delete(Long id) {
        accounts.remove(id.intValue());
    }

    @Override
    public List<Account> find(List<Long> ids) {
        return accounts.stream().filter(a -> ids.contains(a.getId())).collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(Long customerId) {
        return accounts.stream().filter(a -> a.getId().equals(customerId)).findFirst();
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }
}