package com.example.credit.domain.credit;

import com.example.credit.domain.common.exception.CreditException;
import com.example.credit.domain.common.exception.ExceptionMessage;
import com.example.credit.domain.common.model.Status;
import com.example.credit.domain.credit.model.Amount;
import com.example.credit.domain.credit.model.Credit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CreditTest {

    @Test
    void should_get_consumed_credit_when_consumed() {
        //give
        Credit credit = createCredit(Amount.builder()
                .value(new BigDecimal(100))
                .currency("usd")
                .build());

        var consumedAmount = Amount.builder()
                .currency("usd")
                .value(new BigDecimal(20))
                .build();

        // when
        var result = credit.consume(consumedAmount);

        //then
        assertThat(credit.getAmount().getValue()).isEqualTo(new BigDecimal(80));
        assertThat(result.getValue()).isEqualTo(new BigDecimal(20));
    }

    @Test
    void should_throw_exception_when_currency_not_match() {
        //give
        Credit credit = createCredit(Amount.builder()
                .value(new BigDecimal(100))
                .currency("usd")
                .build());

        var consumedAmount = Amount.builder()
                .currency("try")
                .value(new BigDecimal(20))
                .build();

        // when - then
        assertThatExceptionOfType(CreditException.class)
                .isThrownBy(() -> credit.consume(consumedAmount))
                .withMessage(ExceptionMessage.CURRENCY_MISS_MATCH);
    }

    @Test
    void should_deactivate_credit() {
        //give
        Credit credit = createCredit(Amount.builder()
                .value(new BigDecimal(100))
                .currency("usd")
                .build());

        // when
        credit.deactivate();

        //then
        assertThat(credit.getStatus()).isEqualTo(Status.PASSIVE);
    }

    @Test
    void should_revert_amount() {
        //give
        Credit credit = createCredit(Amount.builder()
                .value(new BigDecimal(80))
                .currency("usd")
                .build());

        var revertedAmount = Amount.builder()
                .currency("usd")
                .value(new BigDecimal(20))
                .build();

        // when
        credit.revert(revertedAmount);

        //then
        assertThat(credit.getAmount().getValue()).isEqualTo(new BigDecimal(100));
    }

    @Test
    void should_throw_exception_at_revert_when_currency_not_match() {
        //give
        Credit credit = createCredit(Amount.builder()
                .value(new BigDecimal(80))
                .currency("usd")
                .build());

        var revertedAmount = Amount.builder()
                .currency("try")
                .value(new BigDecimal(20))
                .build();

        // when - then
        assertThatExceptionOfType(CreditException.class)
                .isThrownBy(() -> credit.revert(revertedAmount))
                .withMessage(ExceptionMessage.CURRENCY_MISS_MATCH);
    }

    private Credit createCredit(Amount amount) {
        return Credit.builder()
                .id(1L)
                .status(Status.ACTIVE)
                .userId(100L)
                .version(1L)
                .amount(amount)
                .createdBy("test")
                .expirationDate(LocalDateTime.now().plusDays(10))
                .build();
    }
}
