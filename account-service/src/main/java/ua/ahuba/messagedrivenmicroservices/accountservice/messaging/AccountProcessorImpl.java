package ua.ahuba.messagedrivenmicroservices.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ahuba.messagedrivenmicroservices.accountservice.model.Account;
import ua.ahuba.messagedrivenmicroservices.accountservice.service.AccountService;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;

public class AccountProcessorImpl implements AccountProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountProcessorImpl.class);

    private ObjectMapper mapper = new ObjectMapper();

    private final AccountService accountService;

    public AccountProcessorImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Order process(final Order order) throws JsonProcessingException {
        Account account = accountService.findById(order.getCustomerId());
        LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
        if (order.getPrice() <= account.getBalance()) {
            order.setStatus(OrderStatus.ACCEPTED);
            account.setBalance(account.getBalance() - order.getPrice());
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        LOGGER.info("Account funds were dispensed : {}", mapper.writeValueAsString(account));
        return order;
    }

}
