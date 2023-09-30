package com.example.credit.domain.credit.usecase.command;

import com.example.credit.domain.common.model.UseCase;

public record RemoveCredit(Long creditId) implements UseCase {
}
