package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.service.base.LoginService;
import com.market.servicemarket.util.CommanUtil;
import com.market.servicemarket.util.Constants;
import com.market.servicemarket.util.HashUtil;
import com.market.servicemarket.util.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginBusinessImpl implements LoginBusiness {

    @Autowired
    LoginService loginService;

    @Override
    public BaseResponse authenticateUser(LoginRequest request)throws Exception{

        UserEntity userEntity = loginService.getUser(request.getUsername(), HashUtil.hashString(request.getPassword()));
        if(CommanUtil.isNotNull(userEntity)){

            if(UserConstants.ACTIVE.equals(userEntity.getStatus())){


                


                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(Constants.SUCCESS_RESPONSE_MESSAGE).build();

                return baseResponse;

            }else if(UserConstants.SUSPENDED.equals(userEntity.getStatus())){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.USER_SUSPENDED_RESPONSE_CODE)
                        .responseMessage(Constants.USER_SUSPENDED_RESPONSE_MESSAGE).build();

                return baseResponse;

            }else if(UserConstants.DELETE.equals(userEntity.getStatus())){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                        .responseMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_MESSAGE).build();

                return baseResponse;

            }else{

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                        .responseMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_MESSAGE).build();

                return baseResponse;
            }


        }else{

            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                    .responseMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_MESSAGE).build();

            return baseResponse;

        }

    }

}
