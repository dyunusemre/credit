package com.example.credit.domain.creditoperation.model;

import lombok.Getter;

@Getter
public enum CreditOperationType {
    ADD,
    USE,
    EXPIRE,
    RESERVE,
    RELEASE,
    REMOVE
}
