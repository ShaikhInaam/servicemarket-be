package com.market.servicemarket.repository;

import com.market.servicemarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsernameAndUserPass(String username, String password);

    UserEntity findByUsername(String username);

    @Query("select true from UserEntity u, UserDetailsEntity ud where " +
            "u.username = :username or ud.email = :email or ud.nicNumber = :nicNumber")
    Boolean isUserAvailableByNameOrNicOrEmail(@Param("username") String username,
                                              @Param("email") String email,
                                              @Param("nicNumber") String nicNumber);
}
