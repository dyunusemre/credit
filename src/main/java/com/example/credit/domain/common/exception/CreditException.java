package com.example.credit.domain.common.exception;

import lombok.Getter;

@Getter
public class CreditException extends RuntimeException {

    private final String key;
    private final String[] args;

    public CreditException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public CreditException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}