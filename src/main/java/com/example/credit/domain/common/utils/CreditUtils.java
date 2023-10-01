package com.example.credit.domain.common.utils;

import com.example.credit.domain.credit.model.Amount;

import java.math.BigDecimal;
import java.util.List;

public class CreditUtils {

    public static Amount calculateTotalAmount(List<Amount> amountList) {
        if (amountList.isEmpty())
            return Amount.builder()
                    .value(BigDecimal.ZERO)
                    .currency("usd")
                    .build();

        var value = amountList.stream().map(Amount::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Amount.builder()
                .currency("usd")
                .value(value)
                .build();
    }
}
