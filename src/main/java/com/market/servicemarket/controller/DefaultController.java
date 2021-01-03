package com.market.servicemarket.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.business.base.MenuBusiness;
import com.market.servicemarket.business.impl.MenuBusinessImpl;
import com.market.servicemarket.dto.WeatherJsonResponse;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import com.market.servicemarket.util.RestServiceUtility;
import com.market.servicemarket.util.TransactionLoggerBEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;

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
