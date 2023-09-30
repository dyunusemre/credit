package com.example.credit.infra.adapters.creditoperation.persistence.entity;

import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.creditoperation.model.Reason;
import com.example.credit.infra.common.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "credit_operation")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CreditOperationEntity extends BaseEntity {
    private Long id;
    private Long creditId;
    private String type;
    private BigDecimal amount;
    private String currency;
    private String reason;

    public CreditOperation toCreditOperation(){
        return CreditOperation.builder()
                .id(id)
                .creditId(creditId)
                .creditOperationType(CreditOperationType.valueOf(type))
                .amount(Amount.builder()
                        .value(amount)
                        .currency(currency)
                        .build())
                .reason(Reason.valueOf(reason))
                .createdBy(getCreatedBy())
                .created(getCreated())
                .build();
    }
}
