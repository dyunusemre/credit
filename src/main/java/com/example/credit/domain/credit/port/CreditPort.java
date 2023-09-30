package com.example.credit.domain.credit.port;

import com.example.credit.domain.credit.model.Credit;
import com.example.credit.domain.credit.usecase.command.AddCredit;

import java.util.List;
import java.util.Optional;

public interface CreditPort {
    Credit create(AddCredit useCase);

    void save(Credit credit);

    void delete(Credit credit);

    Optional<Credit> findById(Long creditId);

    List<Credit> findAllActiveCreditByUserId(Long userId);

    void saveAll(List<Credit> credits);

    List<Credit> findAllNonExpiredCredits(int pageSize);
}
