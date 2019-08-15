package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.model.account.Account;

import java.sql.SQLException;

public interface AccountCreateService {
    Account create(Account entity) throws SQLException;
}
