package com.example.credit.domain.creditoperation.port;

import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.model.CreditOperationType;

import java.util.List;

public interface CreditOperationPort {
    void saveAll(List<CreditOperation> creditOperationList);
    List<CreditOperation> retrieveAllCreditOperationByUserId(Long userId);
    void create(Long creditId, CreditOperationType creditOperationType, Amount amount);
    void createTransactionReserveOperation(Long creditId, Long transactionId, CreditOperationType creditOperationType, Amount amount);
    List<CreditOperation> retrieveReservedCreditOperationsByTransactionId(Long transactionId);
}
