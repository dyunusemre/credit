package com.example.credit.domain.common;

import com.example.credit.domain.common.model.UseCase;

public interface UseCaseHandler<E, T extends UseCase> {
    E handle(T useCase);
}
