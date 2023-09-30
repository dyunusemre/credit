package com.example.credit.domain.creditoperation.usecase.command;

import com.example.credit.domain.common.model.UseCase;

public record ReleaseCredit(Long transactionId) implements UseCase {
}
