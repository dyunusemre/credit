package com.example.credit.domain.credit;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.exception.CreditException;
import com.example.credit.domain.common.exception.ExceptionMessage;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.common.utils.CreditUtils;
import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.credit.model.Credit;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.creditoperation.model.CreditOperation;
import com.example.credit.domain.creditoperation.model.CreditOperationType;
import com.example.credit.domain.credit.usecase.command.ReserveCredit;
import com.example.credit.domain.creditoperation.port.CreditOperationPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@DomainComponent
@RequiredArgsConstructor
public class ReserveCreditHandler implements UseCaseHandler<OperationResult<String>, ReserveCredit> {
    private final CreditPort creditPort;
    private final CreditOperationPort creditOperationPort;

    @Override
    public OperationResult<String> handle(ReserveCredit useCase) {
        var requiredAmount = Amount.builder().
                value(useCase.amount())
                .currency("usd")
                .build();

        var availableCredits = creditPort.findAllActiveCreditByUserId(useCase.userId());
        var totalAmount = CreditUtils.calculateTotalAmount(availableCredits.stream().map(Credit::getAmount).toList());
        var reservedCredits = creditOperationPort.retrieveReservedCreditOperationsByTransactionId(useCase.transactionId());
        var reservedTotalAmount = CreditUtils.calculateTotalAmount(reservedCredits.stream().map(CreditOperation::getAmount).toList());

        if (requiredAmount.add(reservedTotalAmount).getValue().compareTo(totalAmount.getValue()) > 0) {
            throw new CreditException(ExceptionMessage.CREDIT_NOT_AVAILABLE);
        }

        for (Credit credit : availableCredits) {
            var consumed = credit.consume(requiredAmount);
            creditPort.save(credit);
            creditOperationPort.createTransactionReserveOperation(credit.getId(), useCase.transactionId(), CreditOperationType.RESERVE, consumed);

            if (requiredAmount.getValue().compareTo(BigDecimal.ZERO) == 0) break;
        }

        return new OperationResult<>(Optional.empty(), OperationStatus.SUCCESS);
    }
}