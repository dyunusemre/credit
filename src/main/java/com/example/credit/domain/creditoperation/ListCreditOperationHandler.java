package com.example.credit.domain.creditoperation;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import com.example.credit.domain.creditoperation.usecase.query.ListCreditOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class ListCreditOperationHandler implements UseCaseHandler<OperationResult<List<CreditOperation>>, ListCreditOperation> {

    private final CreditOperationPort creditOperationPort;

    @Override
    public OperationResult<List<CreditOperation>> handle(ListCreditOperation useCase) {
        List<CreditOperation> creditOperations = creditOperationPort.retrieveAllCreditOperationByUserId(useCase.userId());
        return new OperationResult<>(Optional.of(creditOperations), OperationStatus.SUCCESS);
    }
}
