package com.example.credit.infra.adapters.creditoperation.persistence;

import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import com.example.credit.infra.adapters.creditoperation.persistence.entity.CreditOperationEntity;
import com.example.credit.infra.adapters.creditoperation.persistence.repository.CreditOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreditOperationAdapter implements CreditOperationPort {

    private final CreditOperationRepository creditOperationRepository;

    @Override
    public void saveAll(List<CreditOperation> creditOperationList) {

    }

    @Override
    public List<CreditOperation> retrieveAllCreditOperationByUserId(Long userId) {
        var creditOperations = creditOperationRepository.findCreditOperationsByUserId(userId);
        return creditOperations.stream().map(CreditOperationEntity::toCreditOperation).toList();
    }

    @Override
    public void create(Long creditId, CreditOperationType creditOperationType, Amount amount) {
        var creditOperationEntity = CreditOperationEntity.builder()
                .creditId(creditId)
                .type(creditOperationType.toString())
                .currency(amount.getCurrency())
                .amount(amount.getValue())
                .build();

        creditOperationRepository.save(creditOperationEntity);
    }

    @Override
    public void createTransactionReserveOperation(Long creditId, Long transactionId, CreditOperationType creditOperationType, Amount amount) {

    }

    @Override
    public List<CreditOperation> retrieveReservedCreditOperationsByTransactionId(Long transactionId) {
        return null;
    }

}
