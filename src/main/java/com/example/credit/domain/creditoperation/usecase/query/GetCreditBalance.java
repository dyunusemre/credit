package com.example.credit.domain.creditoperation.usecase.query;

import com.example.credit.domain.common.model.UseCase;

public record GetCreditBalance(Long userId) implements UseCase {
}
