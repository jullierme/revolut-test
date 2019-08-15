package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.model.Account;

import javax.ws.rs.core.Response;
import java.util.Optional;

public interface AccountFindByIdService {
    Optional<Account> find(Long id);
}
