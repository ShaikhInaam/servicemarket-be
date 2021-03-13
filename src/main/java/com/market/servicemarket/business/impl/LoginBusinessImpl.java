package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.dto.UserDetails;
import com.market.servicemarket.dto.UserToken;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.response.LoginResponse;
import com.market.servicemarket.security.JwtUserDetailsService;
import com.market.servicemarket.service.base.UserService;
import com.market.servicemarket.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class LoginBusinessImpl implements LoginBusiness, SecurityConstants {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    UserService userService;


    @Override
    public BaseResponse authenticateUser(LoginRequest request)throws Exception{

        UserEntity userEntity = userService.getUser(request.getUsername(), HashUtil.hashString(request.getPassword()));
        if(CommanUtil.isNotNull(userEntity)){

            if(UserConstants.ACTIVE.equals(userEntity.getStatus())){

                final org.springframework.security.core.userdetails.UserDetails springUserDetails = userDetailsService
                        .loadUserByUsername(userEntity.getUsername());

                // Token Generation
                final String token = jwtTokenUtil.generateToken(springUserDetails);
                UserToken userToken = UserToken.builder().token(token)
                        .validity("15 Minutes").build();


                // User Details Entity
                UserDetailsEntity userDetailsEntity = userEntity.getUserDetailsEntity();
                UserDetails userDetails = null;
                if(CommanUtil.isNotNull(userDetailsEntity)){

                    userDetails = UserDetails.builder().city(userDetailsEntity.getCity()).country(userDetailsEntity.getCountry())
                            .dateOfBirth(userDetailsEntity.getDateOfBirth()+"").email(userDetailsEntity.getEmail())
                            .lastLogin(userDetailsEntity.getLastLogin()+"").nicNumber(userDetailsEntity.getNicNumber())
                            .nicExpiryDate(userDetailsEntity.getNicExpiryDate()+"").build();

                    userDetailsEntity.setLastLogin(new Timestamp(System.currentTimeMillis()));
                    userService.saveUserDetail(userDetailsEntity);
                }

                //Setting up for response
                LoginResponse loginResponse = LoginResponse.builder().name(userEntity.getName())
                        .userToken(userToken).userDetails(userDetails).build();

                return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(loginResponse).build();


            }else if(UserConstants.SUSPENDED.equals(userEntity.getStatus())){

                return BaseResponse.builder().responseCode(Constants.USER_SUSPENDED_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.USER_SUSPENDED_RESPONSE_CODE)).build();

            }

        }

        return BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)).build();

    }

}
