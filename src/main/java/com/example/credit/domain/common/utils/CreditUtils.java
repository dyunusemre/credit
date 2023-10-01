package com.example.credit.domain.common.utils;

import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.credit.model.Credit;
import com.example.credit.domain.creditoperation.model.CreditOperation;

import java.math.BigDecimal;
import java.util.List;

public class CreditUtils {

    public static Amount calculateAvailableTotalAmount(List<Credit> availableCredits) {
        if (availableCredits.isEmpty())
            return Amount.builder()
                    .value(BigDecimal.ZERO)
                    .currency("usd")
                    .build();

        var value = availableCredits.stream().map(credit -> credit.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Amount.builder()
                .currency("usd")
                .value(value)
                .build();
    }

    public static Amount calculateReservedAmount(List<CreditOperation> reservedCredits) {
        if (reservedCredits.isEmpty())
            return Amount.builder()
                    .value(BigDecimal.ZERO)
                    .currency("usd")
                    .build();

        var value = reservedCredits.stream().map(creditOperation -> creditOperation.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Amount.builder()
                .currency("usd")
                .value(value)
                .build();
    }
}
