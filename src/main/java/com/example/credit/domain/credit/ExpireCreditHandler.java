package com.example.credit.domain.credit;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.credit.model.Credit;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.credit.usecase.command.ExpireCredit;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class ExpireCreditHandler implements UseCaseHandler<OperationResult<String>, ExpireCredit> {
    private final CreditPort creditPort;
    private final CreditOperationPort creditOperationPort;

    private final static int pageSize = 200;

    @Override
    public OperationResult<String> handle(ExpireCredit useCase) {
        var creditsToExpire = creditPort.findAllNonExpiredCredits(pageSize);
        creditsToExpire.forEach(Credit::deactivate);
        creditPort.saveAll(creditsToExpire);
        creditsToExpire.forEach(credit -> creditOperationPort.create(credit.getId(), CreditOperationType.EXPIRE, credit.getAmount()));

        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}
