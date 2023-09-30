package com.example.credit.infra.adapters.credit.persistence.entity;

import com.example.credit.domain.common.model.Status;
import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.credit.model.Credit;
import com.example.credit.infra.adapters.creditoperation.persistence.entity.CreditOperationEntity;
import com.example.credit.infra.common.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "credit")
@Data
@Builder
public class CreditEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;
    private Long userId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime expirationDate;
    private boolean status;
    @OneToMany(mappedBy = "creditEntity")
    private List<CreditOperationEntity> creditOperationEntityList;

    public Credit toCredit() {
        return Credit.builder()
                .id(id)
                .amount(Amount.builder()
                        .currency(currency)
                        .value(amount)
                        .build())
                .expirationDate(expirationDate)
                .status(Status.of(status))
                .userId(userId)
                .version(version)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditEntity creditEntity = (CreditEntity) o;
        if (!userId.equals(creditEntity.userId)) return false;
        if (!amount.equals(creditEntity.amount)) return false;
        if (!currency.equals(creditEntity.currency)) return false;
        if (!expirationDate.equals(creditEntity.expirationDate)) return false;
        if (status != creditEntity.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        var result = userId.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + expirationDate.hashCode();
        return result;
    }
}
