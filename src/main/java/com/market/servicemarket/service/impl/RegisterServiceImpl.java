package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserDetailsRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.RegisterService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public Boolean getUser(String username, String email, String nic) {
        Boolean isAvailable = userRepository.isUserAvailableByNameOrNicOrEmail(username, email, nic);
        return isAvailable;
    }


    @Override
    public UserEntity saveUser(UserEntity userEntity) {

        UserEntity entity = userRepository.save(userEntity);

        return entity;
    }


    @Override
    public UserDetailsEntity saveUserDetails(UserDetailsEntity userDetailsEntity) {
        UserDetailsEntity entity = userDetailsRepository.save(userDetailsEntity);

        return entity;
    }
}
