package com.example.credit.domain.creditoperation;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import com.example.credit.domain.creditoperation.usecase.command.ReleaseCredit;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class ReleaseCreditHandler implements UseCaseHandler<OperationResult<String>, ReleaseCredit> {

    private final CreditOperationPort creditOperationPort;
    private final CreditPort creditPort;

    @Override
    public OperationResult<String> handle(ReleaseCredit useCase) {
        var reservedCredits = creditOperationPort.retrieveReservedCreditOperationsByTransactionId(useCase.transactionId());
        for (CreditOperation creditOperation : reservedCredits) {
            creditOperation.release();
            var credit = creditPort.findById(creditOperation.getCreditId());
            if (credit.isPresent()) {
                credit.get().revert(creditOperation.getAmount());
                creditPort.save(credit.get());
            }
        }
        creditOperationPort.saveAll(reservedCredits);
        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}
