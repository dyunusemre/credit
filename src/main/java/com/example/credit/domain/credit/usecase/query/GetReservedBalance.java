package com.example.credit.domain.credit.usecase.query;

import com.example.credit.domain.common.model.UseCase;

public record GetReservedBalance(Long userId) implements UseCase {
}
