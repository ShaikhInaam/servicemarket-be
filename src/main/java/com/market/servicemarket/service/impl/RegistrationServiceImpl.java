package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.RegistrationRepository;
import com.market.servicemarket.repository.UserRepository;
import com.market.servicemarket.service.base.RegistrationService;
import com.market.servicemarket.util.CommanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    UserRepository userRepository;

    UserDetailsEntity userDetailsEntity=null;

    @Override
    public UserDetailsEntity getUserDetail(String username) {

        userDetailsEntity=registrationRepository.findByUsername(username);
        if(CommanUtil.isNotNull(userDetailsEntity)){
            return userDetailsEntity;
        }
        return null;
    }

    @Override
    public UserDetailsEntity getUserByEmail(String email) {

        userDetailsEntity=registrationRepository.findTopByEmail(email);
        if(CommanUtil.isNotNull(userDetailsEntity)){
            return userDetailsEntity;
        }
        return null;
    }

    @Override
    public UserDetailsEntity getUserByNic(String nic) {
        userDetailsEntity=registrationRepository.findTopByNicNumber(nic);
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
        registrationRepository.save(userDetailsEntity);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
