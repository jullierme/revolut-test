package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountCreateServiceFactory")
class AccountCreateServiceFactoryITest {

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
