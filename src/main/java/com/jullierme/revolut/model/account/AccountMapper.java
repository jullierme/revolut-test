package com.jullierme.revolut.model.account;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    AccountDto mapperToAccountDto(Account account);

    Account toAccount(AccountDto account);
}
