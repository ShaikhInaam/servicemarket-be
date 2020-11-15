package com.market.servicemarket.usage_analysis_repository;

import com.market.servicemarket.usage_analysis_entity.UsageAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageAnalysisRepository extends JpaRepository<UsageAnalysisEntity, Integer> {
    UsageAnalysisEntity findTopByOrderByIdDesc();
}
