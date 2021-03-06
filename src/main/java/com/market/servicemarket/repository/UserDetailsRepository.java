package com.market.servicemarket.repository;

import com.market.servicemarket.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {


    UserDetailsEntity findByEmail(String email);
    UserDetailsEntity findByNicNumber(String nicNumber);
    UserDetailsEntity findTopByNicNumber(String nicNumber);

    UserDetailsEntity findTopByEmail(String email);

}
