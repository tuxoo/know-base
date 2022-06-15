package com.home.knowbaseservice.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.home.knowbaseservice.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private UUID id;

    private String name;

    @Id
    @JsonProperty("email")
    private String loginEmail;

    private Instant registeredAt;

    private Instant visitedAt;

    private Role role;

    private boolean isEnabled;
}
