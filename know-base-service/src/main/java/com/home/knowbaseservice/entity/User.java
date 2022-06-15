package com.home.knowbaseservice.entity;

import com.home.knowbaseservice.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login_email", nullable = false)
    private String loginEmail;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Column(name = "visited_at", nullable = false)
    private Instant visitedAt;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;
}
