package com.jullierme.revolut.model.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test suite of the class: TransactionMapperUTest")
public class TransactionMapperUTest {

    private TransactionMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new TransactionMapperImpl();
    }

    @Test
    @DisplayName("Should NOT accept invalid arguments when mapping to dto")
    void givenInvalidTransaction_whenMapToDto_thenShouldThrowException() {
        //given
        Transaction entity = null;

        //when
        Executable toTransactionDto =
                () -> mapper.toTransactionDto(entity);

        //then
        assertThrows(IllegalArgumentException.class, toTransactionDto);
    }

    @Test
    @DisplayName("Should convert a transaction entity into transaction dto")
    void givenTransaction_whenMapToDto_thenShouldConvert() {
        //given
        Long id = 1L;
        Long fromAccountId = 18181818L;
        Long toAccountId = 17171717L;
        BigDecimal amount = TEN;

        Transaction entity = new Transaction();
        entity.setId(id);
        entity.setAmount(amount);
        entity.setFromAccountId(fromAccountId);
        entity.setToAccountId(toAccountId);

        //when
        TransactionDto dto = mapper.toTransactionDto(entity);

        //then
        assertNotNull(dto);
        assertEquals(id, entity.getId());
        assertEquals(fromAccountId, entity.getFromAccountId());
        assertEquals(toAccountId, entity.getToAccountId());
        assertEquals(amount, entity.getAmount());
    }

}
