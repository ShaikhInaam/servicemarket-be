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

}
