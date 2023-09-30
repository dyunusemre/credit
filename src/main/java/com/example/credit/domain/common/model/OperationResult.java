package com.example.credit.domain.common.model;

import java.util.Optional;

public record OperationResult<T>(Optional<T> data, OperationStatus status) {
}
