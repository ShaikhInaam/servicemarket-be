package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserDetailsRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.UserService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    UserDetailsEntity userDetailsEntity=null;

    UserEntity userEntity = null;


    @Override
    public UserDetailsEntity getUserByEmail(String email) {

        userDetailsEntity= userDetailsRepository.findTopByEmail(email);
        if(CommanUtil.isNotNull(userDetailsEntity)){
            return userDetailsEntity;
        }
        return null;
    }

    @Override
    public UserDetailsEntity getUserByNic(String nic) {
        userDetailsEntity= userDetailsRepository.findTopByNicNumber(nic);
        if(CommanUtil.isNotNull(userDetailsEntity)){
            return userDetailsEntity;
        }
        return null;
    }

    @Override
    public UserDetailsEntity getUserOneByEmail(String email) {
        return null;
    }

    @Override
    public void saveUserDetail(UserDetailsEntity userDetailsEntity) {
        userDetailsRepository.save(userDetailsEntity);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public Boolean isUserAvailableByNameOrNicOrEmail(String username, String email, String nicNumber){

        return userRepository.isUserAvailableByNameOrNicOrEmail(username, email, nicNumber);

    }

    @Override
    public UserEntity getUser(String username, String password){

        UserEntity userEntity = null;
        userEntity = userRepository.findByUsernameAndUserPass(username, password);
        if(CommanUtil.isNotNull(userEntity)){
            return userEntity;
        }

        return null;
    }

    //update user information
    @Override
    public UserEntity findByUserName(String username) {

        userEntity = userRepository.findByUsername(username);

        if(CommanUtil.isNotNull(userEntity)){
            return userEntity;
        }

        return null;
    }

    //update user details information
    @Override
    public boolean updateUserDetailsInformation(String email, String nicNumber, String username) {

        return userDetailsRepository.updateUserDetailsInformation(email, nicNumber,username);

    }

}
