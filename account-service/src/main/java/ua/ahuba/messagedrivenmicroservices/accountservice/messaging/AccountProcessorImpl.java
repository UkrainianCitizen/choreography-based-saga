package ua.ahuba.messagedrivenmicroservices.accountservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ua.ahuba.messagedrivenmicroservices.accountservice.service.AccountService;
import ua.ahuba.messaging.Order;
import ua.ahuba.messaging.OrderStatus;

@Slf4j
@AllArgsConstructor
public class AccountProcessorImpl implements AccountProcessor {

    private final ObjectMapper mapper;
    private final AccountService accountService;

    @Override
    public Order process(final Order order) throws JsonProcessingException {
        val account = accountService.findById(order.getCustomerId());
        log.info("Account found: {}", mapper.writeValueAsString(account));
        if (order.getPrice() <= account.getBalance()) {
            order.setStatus(OrderStatus.ACCEPTED);
            account.setBalance(account.getBalance() - order.getPrice());
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        log.info("Account funds were dispensed : {}", mapper.writeValueAsString(account));
        return order;
    }
}