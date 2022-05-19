package com.home.knowbase.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "user_token")
public class UserToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private UUID token;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @OneToOne(mappedBy = "token")
    private User user;
}
