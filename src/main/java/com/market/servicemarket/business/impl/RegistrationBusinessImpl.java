package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.RegistrationBusiness;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.request.RegistrationUser;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.security.JwtUserDetailsService;
import com.market.servicemarket.service.base.RegistrationService;
import com.market.servicemarket.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class RegistrationBusinessImpl implements RegistrationBusiness {
    @Autowired
    RegistrationService registrationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    ConfigurationUtil configurationUtil;


    @Autowired
    UserRepository userRepository;


    @Override
    public BaseResponse registerUser(RegistrationUser registrationUser) throws Exception {
        UserEntity userEntity = new UserEntity();


         UserDetailsEntity userDetailsEntityByUsername = registrationService.getUserDetail(registrationUser.getUsername());
        if(CommanUtil.isNotNull(userDetailsEntityByUsername))
        {

            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).build();
            return baseResponse;


        }
        UserDetailsEntity userDetailsEntityByEmail = registrationService.getUserByEmail(registrationUser.getEmail());
         if(CommanUtil.isNotNull(userDetailsEntityByEmail)){

             BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                     .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).build();
             return baseResponse;
        }
        UserDetailsEntity userDetailsEntityNic = registrationService.getUserByNic(registrationUser.getNicNumber());
        if(CommanUtil.isNotNull(userDetailsEntityNic)){

            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILUARE_RESPNSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE)).build();
            return baseResponse;
        }

        UserDetailsEntity userDetailsEntity = UserDetailsEntity.builder().username(registrationUser.getUsername()).
                id(registrationUser.getId()).nicNumber(registrationUser.getNicNumber()).
                email(registrationUser.getEmail()).dateOfBirth(registrationUser.getDateOfBirth()).
                city(registrationUser.getCity()).country(registrationUser.getCountry()).
                lastLogin(registrationUser.getLastLogin()).nicExpiryDate(registrationUser.getNicExpiryDate()).
                longitude(registrationUser.getLongitude()).latitude(registrationUser.getLatitude()).
                createdDate(registrationUser.getCreatedDate()).build();
        registrationService.saveUserDetail(userDetailsEntity);

        userEntity=UserEntity.builder().username(registrationUser.getUsername()).
                name(registrationUser.getName()).userPass(HashUtil.hashString(registrationUser.getPassword())).
                status(registrationUser.getStatus()).build();
        registrationService.saveUser(userEntity);

        BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();
        System.out.println("successfull");

        return baseResponse;
    }
}
