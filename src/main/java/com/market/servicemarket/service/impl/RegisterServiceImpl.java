package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserDetailsRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.RegisterService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserEntity getUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(CommanUtil.isNotNull(userEntity)){
            return userEntity;
        }
        return null;
    }
}
