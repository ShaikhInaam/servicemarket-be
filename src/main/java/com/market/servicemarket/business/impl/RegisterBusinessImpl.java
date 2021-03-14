package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.RegisterBusiness;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.service.base.RegisterService;
import com.market.servicemarket.util.CommanUtil;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class RegisterBusinessImpl implements RegisterBusiness {

    @Autowired
    RegisterService registerService;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Transactional
    @Override
    public BaseResponse registerUser(RegisterUser request) throws Exception {
        Boolean isAvailable = registerService.getUser(request.getUsername(), request.getEmail(), request.getNicNumber());
        if(CommanUtil.isNotNull(isAvailable) && isAvailable){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();

            return baseResponse;
        }
        else {

            UserEntity userEntity = UserEntity.builder().username(request.getUsername()).name(request.getName())
                    .userPass(request.getPassword()).status(Constants.USER_STATUS_DEFAULT).build();

            userEntity = registerService.saveUser(userEntity);


            long millis=System.currentTimeMillis();
            Date date = new Date(millis);

            UserDetailsEntity userDetailsEntity = UserDetailsEntity.builder().dateOfBirth(request.getDateOfBirth()).nicNumber(request.getNicNumber())
                    .nicExpiryDate(request.getNicExpiryDate()).latitude(request.getLatitude()).longitude(request.getLongitude())
                    .city(request.getCity()).country(request.getCountry()).email(request.getEmail()).createdDate(date).userId(userEntity.getId())
                    .userEntity(userEntity).build();

            userDetailsEntity = registerService.saveUserDetails(userDetailsEntity);

            if(userDetailsEntity != null) {
                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

                return baseResponse;
            }
            else {
                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();

                return baseResponse;
            }

        }
    }
}
