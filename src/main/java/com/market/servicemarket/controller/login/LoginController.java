package com.market.servicemarket.controller.login;

import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginRequest request){
        BaseResponse responseObject = new BaseResponse();
        responseObject.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
        responseObject.setResponseMessage(Constants.SUCCESS_RESPONSE_MESSAGE);
        Map data = new HashMap();
        data.put("access_token",""+ System.currentTimeMillis());
        responseObject.setResponse(data);

        return ResponseEntity.ok(responseObject);
    }
}
