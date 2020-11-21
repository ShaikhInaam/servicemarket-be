package com.market.servicemarket.controller;

import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    @Autowired
    ConfigurationUtil configurationUtil;

    @GetMapping("/update")
    public ResponseEntity<BaseResponse> updateConstants(){

        configurationUtil.updateConstants();
        BaseResponse response = new BaseResponse();
        String value = configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE);
        response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
        response.setResponseMessage(value);

        return ResponseEntity.ok(response);
    }

}
