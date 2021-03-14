package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.UserDetailsEntity;
import com.market.servicemarket.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RegisterService {

    Boolean getUser(String username, String email, String nic);

    UserEntity saveUser(UserEntity userEntity);

    UserDetailsEntity saveUserDetails(UserDetailsEntity userDetailsEntity);
}
