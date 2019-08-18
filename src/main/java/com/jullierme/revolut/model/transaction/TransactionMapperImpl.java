package com.jullierme.revolut.model.transaction;

public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toTransactionDto(Transaction account) {
        if (account == null) {
            throw new IllegalArgumentException();
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId(account.getId());
        transactionDto.setFromAccountId(account.getFromAccountId());
        transactionDto.setToAccountId(account.getToAccountId());
        transactionDto.setAmount(account.getAmount());

        return transactionDto;
    }
}
