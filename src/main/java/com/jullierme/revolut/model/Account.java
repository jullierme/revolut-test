package com.jullierme.revolut.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Account {
    private Long id;
    private String name; /*In a real world, should has a person table*/
    private String accountNumber;
    private String sortCode;
    private BigDecimal balance;
}
