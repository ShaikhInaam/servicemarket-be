package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public interface LoginService {

    UserEntity getUser(String username, String password);

    //last login time to update
    void updateLastLoginTime(int id, Timestamp lastlogin);
}
