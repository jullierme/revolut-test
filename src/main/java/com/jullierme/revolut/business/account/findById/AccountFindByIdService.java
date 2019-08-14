package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.model.Account;

import java.sql.SQLException;

public interface AccountFindByIdService {
    Account findById(Long id) throws SQLException;
}
