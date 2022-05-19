package com.home.knowbase.entity;

import com.home.knowbase.enums.Role;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_token_id", referencedColumnName = "id")
    private UserToken token;
}
