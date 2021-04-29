package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.UserBusiness;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.request.UpdateRegisteredUserRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.security.JwtUserDetailsService;
import com.market.servicemarket.service.base.UserService;
import com.market.servicemarket.util.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    UserService userService;


    @Autowired
    ConfigurationUtil configurationUtil;


    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public BaseResponse registerUser(RegisterUser registerUser) throws Exception {

       Boolean checkUserDetails = userService.isUserAvailableByNameOrNicOrEmail(
               registerUser.getUsername(), registerUser.getEmail(), registerUser.getNicNumber());


       if(Objects.nonNull(checkUserDetails) && checkUserDetails){
           BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                   .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();
           return baseResponse;
       }

        UserEntity userEntity=UserEntity.builder().username(registerUser.getUsername()).
                name(registerUser.getName()).userPass(HashUtil.hashString(registerUser.getPassword())).
                status(UserConstants.ACTIVE).build();

        userService.saveUser(userEntity);

        UserDetailsEntity userDetailsEntity = UserDetailsEntity.builder().userEntity(userEntity).userId(userEntity.getId()).
                nicNumber(registerUser.getNicNumber()).
                email(registerUser.getEmail()).dateOfBirth(registerUser.getDateOfBirth()).
                city(registerUser.getCity()).country(registerUser.getCountry()).
                nicExpiryDate(registerUser.getNicExpiryDate()).
                longitude(registerUser.getLongitude()).latitude(registerUser.getLatitude()).
                createdDate(registerUser.getCreatedDate()).build();

        userService.saveUserDetail(userDetailsEntity);


        BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

        return baseResponse;
    }

    //update user and user details information
    @Transactional
    @Override
    public BaseResponse updateUserAndUserDetailsInformation(UpdateRegisteredUserRequest updateRegisteredUserRequest) throws Exception {


        UserEntity userEntity = userService.findByUserName(updateRegisteredUserRequest.getUsername());

        if(Objects.isNull(userEntity)){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();
            return baseResponse;
        }

        userEntity.setId(userEntity.getId());
        userEntity.setName(updateRegisteredUserRequest.getName());
        userEntity.setStatus(userEntity.getStatus());
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setUserPass(userEntity.getUserPass());

        userService.saveUser(userEntity);

            //check to update user details information
        Boolean updateCheckUserDetails = userService.updateUserDetailsInformation(
                updateRegisteredUserRequest.getUsername(), updateRegisteredUserRequest.getEmail(),
                updateRegisteredUserRequest.getNicNumber());

        if(Objects.nonNull(updateCheckUserDetails) && updateCheckUserDetails){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();
            return baseResponse;
        }

        UserDetailsEntity userDetailsEntity = userEntity.getUserDetailsEntity();

        userDetailsEntity.setId(userDetailsEntity.getId());
        userDetailsEntity.setUserId(userEntity.getId());
        userDetailsEntity.setNicNumber(updateRegisteredUserRequest.getNicNumber());
        userDetailsEntity.setNicExpiryDate(updateRegisteredUserRequest.getNicExpiryDate());
        userDetailsEntity.setCity(updateRegisteredUserRequest.getCity());
        userDetailsEntity.setCountry(userDetailsEntity.getCountry());
        userDetailsEntity.setEmail(updateRegisteredUserRequest.getEmail());
        userDetailsEntity.setCreatedDate(userDetailsEntity.getCreatedDate());
        userDetailsEntity.setDateOfBirth(updateRegisteredUserRequest.getDateOfBirth());
        userDetailsEntity.setLatitude(userDetailsEntity.getLatitude());
        userDetailsEntity.setLongitude(userDetailsEntity.getLongitude());
        userDetailsEntity.setLastLogin(userDetailsEntity.getLastLogin());

        userService.saveUserDetail(userDetailsEntity);

        BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

        return baseResponse;
    }

}
