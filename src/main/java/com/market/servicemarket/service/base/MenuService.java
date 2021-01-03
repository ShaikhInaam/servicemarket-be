package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.MenuEntity;
import com.market.servicemarket.entity.SubMenuEntity;

import java.util.List;

public interface MenuService {

    List<MenuEntity> getAllMenu();
    SubMenuEntity getSubMenu(Integer menuId);


}
