package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.MenuBusiness;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/portal")
public class DefaultController {

    @Autowired
    MenuBusiness menuBusiness;



    @PostMapping("/menu")
    public BaseResponse getMenu(@Valid @RequestBody BaseRequest request)throws Exception{

        return menuBusiness.getMenu(request);

    }




}
