package com.jullierme.revolut.business.account.findById;


import javax.ws.rs.core.Response;

public interface AccountFindByIdRestResponseService {
    Response restFind(Long id);
}
