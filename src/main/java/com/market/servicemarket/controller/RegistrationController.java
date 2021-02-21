package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.RegistrationBusiness;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.request.RegistrationUser;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegistrationController {
 @Autowired
 RegistrationBusiness registrationBusiness;
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@Valid @RequestBody RegistrationUser request)throws Exception{


        BaseResponse responseObject = registrationBusiness.registerUser(request);

        return ResponseEntity.ok(responseObject);
    }
}
