package com.example.credit.domain.credit;

import com.example.credit.domain.common.UseCaseHandler;
import com.example.credit.domain.common.model.DomainComponent;
import com.example.credit.domain.common.model.OperationResult;
import com.example.credit.domain.common.model.OperationStatus;
import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.credit.model.Credit;
import com.example.credit.domain.credit.port.CreditPort;
import com.example.credit.domain.creditoperation.usecase.query.GetCreditBalance;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.credit.domain.common.utils.CreditUtils.calculateTotalAmount;

@DomainComponent
@RequiredArgsConstructor
public class GetCreditBalanceHandler implements UseCaseHandler<OperationResult<Amount>, GetCreditBalance> {
    private final CreditPort creditPort;

    @Override
    public OperationResult<Amount> handle(GetCreditBalance useCase) {
        var availableCredits = creditPort.findAllActiveCreditByUserId(useCase.userId());
        var balance = calculateTotalAmount(availableCredits.stream().map(Credit::getAmount).toList());
        return new OperationResult<>(Optional.of(balance), OperationStatus.SUCCESS);
    }
}
