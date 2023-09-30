package com.example.credit.infra.adapters.creditoperation.persistence.repository;

import com.example.credit.infra.adapters.creditoperation.persistence.entity.CreditOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditOperationRepository extends JpaRepository<CreditOperationEntity, Long> {
    List<CreditOperationEntity> findCreditOperationsByUserId(Long userId);
}
