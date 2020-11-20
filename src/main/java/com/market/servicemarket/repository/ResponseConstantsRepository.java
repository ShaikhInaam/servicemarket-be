package com.market.servicemarket.repository;

import com.market.servicemarket.entity.ResponseConstantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseConstantsRepository extends JpaRepository<ResponseConstantsEntity, Integer> {
    List<ResponseConstantsEntity> findAll();
}
