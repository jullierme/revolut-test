package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.model.account.Account;

import java.util.Optional;

public interface AccountFindByIdService {
    Optional<Account> find(Long id);
}
