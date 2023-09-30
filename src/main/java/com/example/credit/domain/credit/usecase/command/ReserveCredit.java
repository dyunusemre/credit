package com.example.credit.domain.credit.usecase.command;

import com.example.credit.domain.common.model.UseCase;

import java.math.BigDecimal;

public record ReserveCredit(Long userId, Long transactionId, BigDecimal amount) implements UseCase {
}
