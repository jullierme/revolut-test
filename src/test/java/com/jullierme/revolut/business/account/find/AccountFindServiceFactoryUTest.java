package com.jullierme.revolut.business.account.find;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test suite of the class: AccountFindServiceFactory")
class AccountFindServiceFactoryUTest {
    @Test
    @DisplayName("Should return a new AccountFindByAccountService instance")
    void givenAccountFindServiceFactory_whenGetAccountFindByIdService_thenShouldReturnNewInstance() {
        //given
        AccountFindServiceFactory factory = AccountFindServiceFactory.getInstance();

        //when
        AccountFindByIdService service = factory.getAccountFindByIdService();

        //then
        assertNotNull(service);
    }

    @Test
    @DisplayName("Should return a new AccountFindByAccountService instance")
    void givenAccountFindServiceFactory_whenGetAccountFindByAccountService_thenShouldReturnNewInstance() {
        //given
        AccountFindServiceFactory factory = AccountFindServiceFactory.getInstance();

        //when
        AccountFindByAccountService service = factory.getAccountFindByAccountService();

        //then
        assertNotNull(service);
    }
}
