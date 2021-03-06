package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserDetailsRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.UserService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    UserDetailsEntity userDetailsEntity=null;


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

}
