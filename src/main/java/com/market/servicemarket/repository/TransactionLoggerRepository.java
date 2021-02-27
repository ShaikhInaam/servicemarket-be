package com.market.servicemarket.repository;

import com.market.servicemarket.entity.TransactionLoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoggerRepository extends JpaRepository<TransactionLoggerEntity, Integer> {



}
