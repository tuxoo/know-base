package com.home.knowbaseservice.repository;

import com.home.knowbaseservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.login = :login")
    Optional<User> findUserIdByLogin(@Param("login") String login);

    @Query("""
            SELECT u FROM User u 
            JOIN UserToken ut ON u.id = ut.id
            WHERE ut.token = :token
            """)
    Optional<User> findUserByToken(@Param("token") UUID token);
}
