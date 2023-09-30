package com.example.credit.domain.creditoperation.model;

import lombok.Getter;

@Getter
public enum Reason {
    TYPE_APPEASEMENT,
    TYPE_PROMOTION,
    TYPE_AUTOMATED,
    TYPE_RESERVATION,
    TYPE_PARTNER_CREDIT;
}
