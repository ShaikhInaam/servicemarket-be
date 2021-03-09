package com.market.servicemarket.business.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.dto.UserDetails;
import com.market.servicemarket.dto.UserToken;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.response.LoginResponse;
import com.market.servicemarket.security.JwtUserDetailsService;
import com.market.servicemarket.service.base.LoginService;
import com.market.servicemarket.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class LoginBusinessImpl implements LoginBusiness, SecurityConstants {

    @Autowired
    LoginService loginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    ConfigurationUtil configurationUtil;


    @Override
    public BaseResponse authenticateUser(LoginRequest request)throws Exception{

        UserEntity userEntity = loginService.getUser(request.getUsername(), HashUtil.hashString(request.getPassword()));
        if(CommanUtil.isNotNull(userEntity)){

            if(UserConstants.ACTIVE.equals(userEntity.getStatus())){

                final org.springframework.security.core.userdetails.UserDetails springUserDetails = userDetailsService
                        .loadUserByUsername(userEntity.getUsername());

                final String token = jwtTokenUtil.generateToken(springUserDetails);

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setName(userEntity.getName());

                UserToken userToken = UserToken.builder().token(token)
                        .validity("15 Minutes").build();
                loginResponse.setUserToken(userToken);


                UserDetailsEntity userDetailsEntity = userEntity.getUserDetailsEntity();//loginService.getUserDetails(userEntity.getUsername());
                if(CommanUtil.isNotNull(userDetailsEntity)){

                    UserDetails userDetails = UserDetails.builder().city(userDetailsEntity.getCity()).country(userDetailsEntity.getCountry())
                            .dateOfBirth(userDetailsEntity.getDateOfBirth()+"").email(userDetailsEntity.getEmail())
                            .lastLogin(userDetailsEntity.getLastLogin()+"").nicNumber(userDetailsEntity.getNicNumber())
                            .nicExpiryDate(userDetailsEntity.getNicExpiryDate()+"").build();

                    loginResponse.setUserDetails(userDetails);
                }


                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(loginResponse).build();

                //updating last login time
                //on successful login
                if(baseResponse.getResponseCode().equals("00100")){

                   // UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
                  //  int idOfUserDetails = userDetailsEntity.getId();
                    int idOfUser = userEntity.getId();
                    Timestamp lastlogintime = new Timestamp(System.currentTimeMillis());
                    loginService.updateLastLoginTime(idOfUser,lastlogintime);

                }

                return baseResponse;



            }else if(UserConstants.SUSPENDED.equals(userEntity.getStatus())){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.USER_SUSPENDED_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.USER_SUSPENDED_RESPONSE_CODE)).build();

                return baseResponse;

            }else if(UserConstants.DELETE.equals(userEntity.getStatus())){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)).build();

                return baseResponse;

            }else{

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)).build();

                return baseResponse;
            }


        }else{

            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.AUTHENTICATION_FAIL_RESPONSE_CODE)).build();

            return baseResponse;

        }

    }

}
