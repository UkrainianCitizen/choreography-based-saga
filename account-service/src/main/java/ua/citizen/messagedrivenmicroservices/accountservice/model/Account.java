package ua.citizen.messagedrivenmicroservices.accountservice.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private int balance;
    private Long customerId;

    public Account(Long customerId, int balance) {
        this.balance = balance;
        this.customerId = customerId;
    }
}
