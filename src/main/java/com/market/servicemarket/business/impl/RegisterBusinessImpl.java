package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.RegisterBusiness;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.service.base.RegisterService;
import com.market.servicemarket.util.CommanUtil;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterBusinessImpl implements RegisterBusiness {

    @Autowired
    RegisterService registerService;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Override
    public BaseResponse registerUser(RegisterUser request) throws Exception {
        UserEntity userEntity = registerService.getUser(request.getUsername());
        if(CommanUtil.isNotNull(userEntity)){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();

            return baseResponse;
        }
        else {
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

            return baseResponse;
        }
    }
}
