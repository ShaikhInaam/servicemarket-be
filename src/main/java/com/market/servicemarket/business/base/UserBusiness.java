package com.market.servicemarket.business.base;

import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.request.UpdateUserInfoRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserBusiness {
    BaseResponse registerUser(RegisterUser registerUser)throws Exception ;

    //update user and user details information
    BaseResponse updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) throws Exception;

}
