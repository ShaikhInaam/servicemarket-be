package com.market.servicemarket.service.impl;

import com.market.servicemarket.dto.Menu;
import com.market.servicemarket.entity.MenuEntity;
import com.market.servicemarket.entity.SubMenuEntity;
import com.market.servicemarket.repository.MenuRepository;
import com.market.servicemarket.repository.SubMenuRepository;
import com.market.servicemarket.service.base.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    MenuRepository menuRepository;

    @Autowired
    SubMenuRepository subMenuRepository;


    @Override
    public List<MenuEntity>  getAllMenu(){

        return menuRepository.findAll();

    }

    @Override
    public SubMenuEntity getSubMenu(Integer menuId){

        return subMenuRepository.findByMenuId(menuId);


    }


}
