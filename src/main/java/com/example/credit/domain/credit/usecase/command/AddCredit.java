package com.example.credit.domain.credit.usecase.command;

import com.example.credit.domain.common.model.UseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AddCredit(Long userId, String currency, BigDecimal amount, String agentId,
                        LocalDateTime expirationDate) implements UseCase {
}
