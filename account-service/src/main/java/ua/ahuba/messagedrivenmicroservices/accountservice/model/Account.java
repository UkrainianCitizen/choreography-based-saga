package ua.ahuba.messagedrivenmicroservices.accountservice.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String number;
    private int balance;
    private Long customerId;

    public Account(String number, int balance, Long customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }
}
