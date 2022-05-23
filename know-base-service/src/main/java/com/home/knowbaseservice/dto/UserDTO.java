package com.home.knowbaseservice.dto;

import java.io.Serializable;

public record UserDTO(Long id, String login, String password) implements Serializable {
}
