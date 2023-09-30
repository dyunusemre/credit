package com.example.credit.domain.credit;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.exception.CreditException;
import com.example.credit.domain.common.exception.ExceptionMessage;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.credit.usecase.command.RemoveCredit;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class RemoveCreditHandler implements UseCaseHandler<OperationResult<String>, RemoveCredit> {
    private final CreditPort creditPort;
    private final CreditOperationPort creditOperationPort;

    @Override
    public OperationResult<String> handle(RemoveCredit useCase) {
        var credit = creditPort.findById(useCase.creditId());
        if (credit.isPresent()) {
            creditPort.delete(credit.get());
            creditOperationPort.create(credit.get().getId(), CreditOperationType.REMOVE, credit.get().getAmount());
        } else
            throw new CreditException(ExceptionMessage.NOT_FOUND);

        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}
