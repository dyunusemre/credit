package com.example.credit.domain.credit;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.credit.usecase.command.AddCredit;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class AddCreditHandler implements UseCaseHandler<OperationResult<String>, AddCredit> {
    private final CreditPort creditPort;
    private final CreditOperationPort creditOperationPort;

    @Override
    public OperationResult<String> handle(AddCredit addCredit) {
        var credit = creditPort.create(addCredit);
        creditOperationPort.create(credit.getId(), CreditOperationType.ADD, credit.getAmount());

        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}
