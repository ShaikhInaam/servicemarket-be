package com.market.servicemarket.repository;

import com.market.servicemarket.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {

    UserDetailsEntity findTopByNicNumber(String nicNumber);
    UserDetailsEntity findTopByEmail(String email);

    //query to update user details of  registration information
    @Query("select true from UserEntity u, UserDetailsEntity ud  " +
            "ud.email = :email and ud.nicNumber = :nicNumber where u.username <>:username ")
    boolean updateUserDetailsInformation(@Param("email") String email,
                                         @Param("nicNumber") String nicNumber,
                                           @Param("username") String username
                                           );
}
