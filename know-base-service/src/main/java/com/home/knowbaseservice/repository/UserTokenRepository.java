package com.home.knowbaseservice.repository;

import com.home.knowbaseservice.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findUserTokenByUserId(Long userId);
}
