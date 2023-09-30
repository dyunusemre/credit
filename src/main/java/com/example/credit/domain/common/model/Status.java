package com.example.credit.domain.common.model;

import java.util.Arrays;

public enum Status {
    ACTIVE(1),
    PASSIVE(0);

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public static Status of(Integer value) {
        return Arrays.stream(Status.values())
                .filter(status -> status.value.equals(value)).findAny().orElseThrow();
    }

    public static Status of(Boolean bool){
        return bool ? Status.ACTIVE : Status.PASSIVE;
    }

}
