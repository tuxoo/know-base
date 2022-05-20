package com.home.knowbase.dto;

import java.io.Serializable;

public record UserDTO(Long id, String login, String password) implements Serializable {
}
