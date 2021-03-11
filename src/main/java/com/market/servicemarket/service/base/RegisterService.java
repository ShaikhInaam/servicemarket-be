package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {

    UserEntity getUser(String username);
}
