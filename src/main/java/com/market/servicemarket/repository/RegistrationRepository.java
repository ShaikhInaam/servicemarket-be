package com.market.servicemarket.repository;

import com.market.servicemarket.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegistrationRepository extends JpaRepository<UserDetailsEntity,String> {
    UserDetailsEntity findByUsername(String username);
    UserDetailsEntity findTopByNicNumber(String nicNumber);

    UserDetailsEntity findTopByEmail(String email);

}
