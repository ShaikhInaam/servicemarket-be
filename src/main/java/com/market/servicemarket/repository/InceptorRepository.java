package com.market.servicemarket.repository;

import com.market.servicemarket.entity.InceptorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InceptorRepository extends JpaRepository<InceptorInfo,Long> {
}
