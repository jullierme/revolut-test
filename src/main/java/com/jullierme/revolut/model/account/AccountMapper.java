package com.jullierme.revolut.model.account;

public interface AccountMapper {
    Account toAccount(AccountDto account);

    AccountDto toAccountDto(Account entity);
}
