package com.market.servicemarket.business.impl;

import com.market.servicemarket.business.base.UserBusiness;
import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.request.UpdateUserInfoRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.security.JwtUserDetailsService;
import com.market.servicemarket.service.base.UserService;
import com.market.servicemarket.util.*;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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
    public BaseResponse updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) throws Exception {


        UserEntity userEntity = userService.findByUserName(updateUserInfoRequest.getUsername());

        if(Objects.isNull(userEntity)){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();
            return baseResponse;
        }

        if(Objects.nonNull(updateUserInfoRequest.getName())){
            userEntity.setName(updateUserInfoRequest.getName());
        }

            //check to update user details information
        Boolean updateCheckUserDetails = userService.updateUserDetailsInformation(
                updateUserInfoRequest.getUsername(), updateUserInfoRequest.getEmail(),
                updateUserInfoRequest.getNicNumber());

        if(Objects.nonNull(updateCheckUserDetails) && updateCheckUserDetails){
            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.FAILURE_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.FAILURE_RESPONSE_CODE)).build();
            return baseResponse;
        }

        UserDetailsEntity userDetailsEntity = userEntity.getUserDetailsEntity();

        if(Objects.nonNull(updateUserInfoRequest.getNicNumber())){

            userDetailsEntity.setNicNumber(updateUserInfoRequest.getNicNumber());
        }

        if(Objects.nonNull(updateUserInfoRequest.getNicExpiryDate())){
            userDetailsEntity.setNicExpiryDate(updateUserInfoRequest.getNicExpiryDate());
        }

        if(Objects.nonNull(updateUserInfoRequest.getDateOfBirth())){
            userDetailsEntity.setDateOfBirth(updateUserInfoRequest.getDateOfBirth());
        }
        if(Objects.nonNull(updateUserInfoRequest.getCity())){

            userDetailsEntity.setCity(updateUserInfoRequest.getCity());
        }

        if(Objects.nonNull(updateUserInfoRequest.getCountry())){

            userDetailsEntity.setCountry(updateUserInfoRequest.getCountry());
        }

        if(Objects.nonNull(updateUserInfoRequest.getEmail())){

            userDetailsEntity.setEmail(updateUserInfoRequest.getEmail());
        }

        userService.saveUser(userEntity);
        userService.saveUserDetail(userDetailsEntity);

        BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).build();

        return baseResponse;
    }

}
