package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginBusiness loginBusiness;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginRequest request)throws Exception{


        BaseResponse responseObject = loginBusiness.authenticateUser(request);

        return ResponseEntity.ok(responseObject);
    }
}
