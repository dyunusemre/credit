package com.example.credit.domain.creditoperation.model;

import com.example.credit.domain.credit.model.Amount;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreditOperation {
    private Long id;
    private Long creditId;
    private Long transactionId;
    private CreditOperationType creditOperationType;
    private Amount amount;
    private Reason reason;
    private String createdBy;
    private LocalDateTime created;

    public void use(){
        creditOperationType = CreditOperationType.USE;
    }

    public void release() {
        creditOperationType = CreditOperationType.RELEASE;
    }
}
