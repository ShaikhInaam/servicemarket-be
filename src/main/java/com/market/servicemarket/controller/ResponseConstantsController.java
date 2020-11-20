package com.market.servicemarket.controller;

import com.market.servicemarket.entity.ResponseConstantsEntity;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.service.base.ResponseConstantsService;
import com.market.servicemarket.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseConstantsController {
    @Autowired
    private ResponseConstantsService constantsService;

    @GetMapping("/updateConstants")
    public ResponseEntity<BaseResponse> updateConstants(){
        constantsService.updateConstants();
        BaseResponse response = new BaseResponse();
        ResponseConstantsEntity entity = constantsService.findConstantsByCode(Constants.SUCCESS_RESPONSE_CODE);
        response.setResponseCode(entity.getCode());
        response.setResponseMessage(entity.getMessage());

        return ResponseEntity.ok(response);
    }

}
