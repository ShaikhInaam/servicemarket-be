package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.dto.UserDetails;
import com.market.servicemarket.dto.UserToken;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.response.LoginResponse;
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


                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setName(userEntity.getName());

                UserToken userToken = UserToken.builder().token(System.currentTimeMillis()+userEntity.getUsername())
                        .validity("2 Hours").build();
                loginResponse.setUserToken(userToken);


                UserDetailsEntity userDetailsEntity = loginService.getUserDetails(userEntity.getUsername());
                if(CommanUtil.isNotNull(userDetailsEntity)){

                    UserDetails userDetails = UserDetails.builder().city(userDetailsEntity.getCity()).country(userDetailsEntity.getCountry())
                            .dateOfBirth(userDetailsEntity.getDateOfBirth()+"").email(userDetailsEntity.getEmail())
                            .lastLogin(userDetailsEntity.getLastLogin()+"").nicNumber(userDetailsEntity.getNicNumber())
                            .nicExpiryDate(userDetailsEntity.getNicExpiryDate()+"").build();

                    loginResponse.setUserDetails(userDetails);
                }


                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(Constants.SUCCESS_RESPONSE_MESSAGE).response(loginResponse).build();

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
