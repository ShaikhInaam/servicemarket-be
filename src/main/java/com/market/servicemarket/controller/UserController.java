package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.UserBusiness;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.request.UpdateUserInfoRequest;
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
public class UserController {
 @Autowired
 UserBusiness userBusiness;
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@Valid @RequestBody RegisterUser request)throws Exception{

        BaseResponse responseObject = userBusiness.registerUser(request);
        return ResponseEntity.ok(responseObject);
    }

    //api to update user information
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateUserInfo(
                                  @Valid @RequestBody UpdateUserInfoRequest request) throws Exception{

        BaseResponse response = userBusiness.updateUserInfo(request);
        return ResponseEntity.ok(response);
    }
}
