package com.market.servicemarket.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.business.base.MenuBusiness;
import com.market.servicemarket.dto.Menu;
import com.market.servicemarket.dto.MenuJsonResponse;
import com.market.servicemarket.entity.MenuEntity;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.service.base.MenuService;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuBusinessImpl implements MenuBusiness {


    @Autowired
    MenuService menuService;

    @Autowired
    ConfigurationUtil configurationUtil;


    @Override
    public BaseResponse getMenu(BaseRequest request)throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Menu> menuList = new ArrayList<>();
        List<MenuEntity> menuEntities = menuService.getAllMenu();
        if (menuEntities != null && menuEntities.size() > 0) {

            for (MenuEntity entity : menuEntities) {
                Menu menu = new Menu();
                menu.setIsActive(entity.getIsActive());
                menu.setMenu(entity.getName());
                if (menuService.getSubMenu(entity.getId()) != null) {
                    String subMenuValue = menuService.getSubMenu(entity.getId()).getValue();
                    subMenuValue = subMenuValue.replaceAll("\\\\", "");
                    List<MenuJsonResponse> subMenuJson = new ObjectMapper().readValue(subMenuValue, List.class);
                    menu.setSubMenu(subMenuJson);
                }

                menuList.add(menu);

            }

        }
        BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(menuList).build();

        return baseResponse;

    }

}
