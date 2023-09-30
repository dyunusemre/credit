package com.example.credit.domain.creditoperation;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import com.example.credit.domain.creditoperation.usecase.command.UseCredit;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class UseCreditHandler implements UseCaseHandler<OperationResult<String>, UseCredit> {
    private final CreditOperationPort creditOperationPort;
    @Override
    public OperationResult<String> handle(UseCredit useCase) {
        var reservedCredits = creditOperationPort.retrieveReservedCreditOperationsByTransactionId(useCase.transactionId());
        reservedCredits.forEach(CreditOperation::use);
        creditOperationPort.saveAll(reservedCredits);
        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}
