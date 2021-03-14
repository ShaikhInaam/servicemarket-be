package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.RegisterBusiness;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    RegisterBusiness registerBusiness;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@Valid @RequestBody RegisterUser request)throws Exception{


        BaseResponse responseObject = registerBusiness.registerUser(request);

        return ResponseEntity.ok(responseObject);
    }
}
