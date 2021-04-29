package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public interface UserService {

    void saveUserDetail(UserDetailsEntity userDetailsEntity);
    void saveUser(UserEntity userEntity);
    Boolean isUserAvailableByNameOrNicOrEmail(String username, String email, String nicNumber);

    //update user information
    UserEntity findByUserName(String username);


    //update user details information
    boolean updateUserDetailsInformation(String email, String nicNumber, String username);
    UserEntity getUser(String username, String password);
}
