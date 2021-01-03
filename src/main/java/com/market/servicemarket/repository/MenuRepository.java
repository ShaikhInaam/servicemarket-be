package com.market.servicemarket.repository;

import com.market.servicemarket.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
}
