package com.market.servicemarket.repository;

import com.market.servicemarket.entity.TransactionLoggerBeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoggerBeRepository extends JpaRepository<TransactionLoggerBeEntity, Integer> {



}
