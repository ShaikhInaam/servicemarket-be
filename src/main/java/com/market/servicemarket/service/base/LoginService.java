package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    UserEntity getUser(String username, String password);
    UserDetailsEntity getUserDetails(String username);
}
