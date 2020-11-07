package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;

import com.market.servicemarket.repository.UserDetailsRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.LoginService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserEntity getUser(String username, String password){

        UserEntity userEntity = null;
        userEntity = userRepository.findByUsernameAndUserPass(username, password);
        if(CommanUtil.isNotNull(userEntity)){
            return userEntity;
        }

        return null;
    }


    @Override
    public UserDetailsEntity getUserDetails(String username){

        UserDetailsEntity userDetailsEntity = null;
        userDetailsEntity  = userDetailsRepository.findByUsername(username);
        if(CommanUtil.isNotNull(userDetailsEntity)){
            return userDetailsEntity;
        }

        return null;
    }

}
