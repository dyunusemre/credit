package com.example.credit.domain.credit.model;

import com.example.credit.domain.common.exception.CreditException;
import com.example.credit.domain.common.exception.ExceptionMessage;
import com.example.credit.domain.common.model.Status;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Credit {
    private Long id;
    private Long userId;
    private Amount amount;
    private LocalDateTime expirationDate;
    private Status status;
    private Long version;
    private String createdBy;

    public void deactivate() {
        status = Status.PASSIVE;
    }

    public Amount consume(Amount consumedAmount) {
        if (!amount.getCurrency().equals(consumedAmount.getCurrency()))
            throw new CreditException(ExceptionMessage.CURRENCY_MISS_MATCH);

        BigDecimal usedAmount;
        if (amount.getValue().compareTo(consumedAmount.getValue()) > 0) {
            usedAmount = consumedAmount.getValue();
            this.amount = Amount.builder()
                    .value(amount.getValue().subtract(consumedAmount.getValue()))
                    .currency(amount.getCurrency())
                    .build();
        } else {
            usedAmount = amount.getValue();
            this.amount = Amount.builder()
                    .value(BigDecimal.ZERO)
                    .currency(amount.getCurrency())
                    .build();
        }
        return Amount.builder()
                .currency(amount.getCurrency())
                .value(usedAmount)
                .build();
    }

    public void revert(Amount addedAmount) {
        if (!amount.getCurrency().equals(addedAmount.getCurrency()))
            throw new CreditException(ExceptionMessage.CURRENCY_MISS_MATCH);

        amount = Amount.builder()
                .value(amount.getValue().add(addedAmount.getValue()))
                .currency(amount.getCurrency())
                .build();

    }
}
