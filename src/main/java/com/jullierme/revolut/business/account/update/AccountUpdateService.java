package com.jullierme.revolut.business.account.update;

import com.jullierme.revolut.model.Account;

import java.sql.SQLException;

public interface AccountUpdateService {
    Account update(Account entity) throws SQLException;
    Account update(Long id, Account entity) throws SQLException;
}
