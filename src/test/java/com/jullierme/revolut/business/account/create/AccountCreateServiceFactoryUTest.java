package com.jullierme.revolut.business.account.create;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test suite of the class: AccountCreateServiceFactory")
class AccountCreateServiceFactoryUTest {

    @Test
    @DisplayName("Should return a new AccountCreateService instance")
    void givenAccountCreateServiceFactory_whenAccountCreateService_thenShouldReturnNewInstance() {
        //given
        AccountCreateServiceFactory factory = AccountCreateServiceFactory.instance();

        //when
        AccountCreateService service = factory.getAccountCreateService();

        //then
        assertNotNull(service);
    }
}
