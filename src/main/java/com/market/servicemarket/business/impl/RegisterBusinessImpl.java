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

            UserEntity userEntity = new UserEntity();
            UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

            userEntity.setUsername(request.getUsername());
            userEntity.setName(request.getName());
            userEntity.setUserPass(request.getPassword());
            userEntity.setStatus(Constants.USER_STATUS_DEFAULT);

            userEntity = registerService.saveUser(userEntity);

            if(userEntity != null) {

                userDetailsEntity.setDateOfBirth(request.getDateOfBirth());
                userDetailsEntity.setNicNumber(request.getNicNumber());
                userDetailsEntity.setNicExpiryDate(request.getNicExpiryDate());
                userDetailsEntity.setLatitude(request.getLatitude());
                userDetailsEntity.setLongitude(request.getLongitude());
                userDetailsEntity.setCity(request.getCity());
                userDetailsEntity.setCountry(request.getCountry());
                userDetailsEntity.setEmail(request.getEmail());

                long millis=System.currentTimeMillis();
                Date date = new Date(millis);
                userDetailsEntity.setCreatedDate(date);
                userDetailsEntity.setUserId(userEntity.getId());
                userDetailsEntity.setUserEntity(userEntity);

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
            else {
                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();

                return baseResponse;
            }
        }
    }
}
