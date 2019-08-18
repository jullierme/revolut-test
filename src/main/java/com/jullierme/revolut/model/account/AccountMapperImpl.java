package com.jullierme.revolut.model.account;


public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(AccountDto account) {
        if (account == null) {
            throw new IllegalArgumentException();
        }

        Account entity = new Account();

        entity.setId(account.getId());
        entity.setName(account.getName());
        entity.setBalance(account.getBalance());

        return entity;
    }

    @Override
    public AccountDto toAccountDto(Account entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }

        AccountDto dto = new AccountDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBalance(entity.getBalance());

        return dto;
    }
}
