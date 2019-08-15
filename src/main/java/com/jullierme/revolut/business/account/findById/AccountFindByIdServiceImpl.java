package com.jullierme.revolut.business.account.findById;

import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.model.Account;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountFindByIdServiceImpl implements AccountFindByIdService,
        AccountFindByIdRestResponseService {
    private static final String FIND_ACCOUNT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE ID = ?";

    private DatabaseConnectionService databaseConnectionService;

    public AccountFindByIdServiceImpl(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @Override
    public Response restFind(Long id) {
        try {
            return find(id)
                    .map(entity -> Response
                            .status(Response.Status.OK)
                            .entity(entity)
                            .build())
                    .orElse(Response
                            .status(Response.Status.NOT_FOUND)
                            .build());
        } catch (Exception e) {
            String message = "There was an internal server error";

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }

    @Override
    public Optional<Account> find(Long id) {
        try (Connection conn = databaseConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ACCOUNT_BY_ID_SQL)) {
            conn.setAutoCommit(true);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Account entity = null;

            while (rs.next()) {
                entity = new Account(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("ACCOUNT_NUMBER"),
                        rs.getString("SORT_CODE"),
                        rs.getBigDecimal("BALANCE"));

            }

            rs.close();

            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
