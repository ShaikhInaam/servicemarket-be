package com.market.servicemarket.business.base;

import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginBusiness {

    BaseResponse authenticateUser(LoginRequest request)throws Exception;
}
