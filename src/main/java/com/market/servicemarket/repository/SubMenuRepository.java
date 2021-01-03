package com.market.servicemarket.repository;

import com.market.servicemarket.entity.MenuEntity;
import com.market.servicemarket.entity.SubMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenuEntity, Integer> {


    SubMenuEntity findByMenuId(Integer menuId);

}
