package com.market.servicemarket.security;

import java.util.ArrayList;

import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);
        return new User(userEntity.getUsername(), userEntity.getUserPass(),
                new ArrayList<>());
    }
}
