package com.jullierme.revolut.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test suite of the class: AccountMapperUTest")
public class AccountMapperUTest {

    private AccountMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new AccountMapperImpl();
    }

    @Test
    @DisplayName("Should NOT accept invalid arguments when mapping to dto")
    void givenInvalidAccount_whenMapToDto_thenShouldThrowException() {
        //given
        Account entity = null;

        //when
        Executable toAccountDto =
                () -> mapper.toAccountDto(entity);

        //then
        assertThrows(IllegalArgumentException.class, toAccountDto);
    }

    @Test
    @DisplayName("Should NOT accept invalid arguments when mapping to account")
    void givenInvalidAccountDto_whenMapToEntity_thenShouldThrowException() {
        //given
        AccountDto dto = null;

        //when
        Executable toAccount =
                () -> mapper.toAccount(dto);

        //then
        assertThrows(IllegalArgumentException.class, toAccount);
    }

    @Test
    @DisplayName("Should convert an account entity into account dto")
    void givenAccount_whenMapToDto_thenShouldConvert() {
        //given
        Long id = 1L;
        String name = "Jullierme";
        BigDecimal balance = TEN;

        Account entity = new Account();
        entity.setId(id);
        entity.setName(name);
        entity.setBalance(balance);

        //when
        AccountDto dto = mapper.toAccountDto(entity);

        //then
        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(balance, dto.getBalance());
    }

    @Test
    @DisplayName("Should convert an account dto into account entity")
    void givenAccountDto_whenMapToEntity_thenShouldConvert() {
        //given
        Long id = 1L;
        String name = "Jullierme";
        BigDecimal balance = TEN;

        AccountDto dto = new AccountDto();
        dto.setId(id);
        dto.setName(name);
        dto.setBalance(balance);

        //when
        Account entity = mapper.toAccount(dto);

        //then
        assertNotNull(dto);
        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(balance, entity.getBalance());
    }
}
