package com.example.credit.domain.credit.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Amount {
    BigDecimal value;
    String currency;

    public Amount add(Amount amount) {
        return Amount.builder()
                .currency(amount.getCurrency())
                .value(value.add(amount.getValue()))
                .build();
    }
}
