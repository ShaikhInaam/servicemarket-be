package com.market.servicemarket.repository;

import com.market.servicemarket.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {


    UserDetailsEntity findByEmail(String email);
    UserDetailsEntity findByNicNumber(String nicNumber);
    UserDetailsEntity findTopByNicNumber(String nicNumber);

    UserDetailsEntity findTopByEmail(String email);


    //query to update last login time
    @Modifying
    @Query("UPDATE UserDetailsEntity ud SET ud.lastLogin = :lastLogin WHERE ud.userId = :id")
    void updateLastLoginTime(@Param("id") int id, @Param("lastLogin") Timestamp lastLogin);


}
