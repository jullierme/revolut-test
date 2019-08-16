package com.jullierme.revolut.model.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper( TransactionMapper.class );

    TransactionDto toTransactionDto(Transaction account);

    Transaction toTransaction(TransactionDto account);
}
