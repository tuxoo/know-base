package com.home.knowbase.service;

import com.home.knowbase.dto.UserDTO;
import com.home.knowbase.entity.User;
import com.home.knowbase.mapper.UserMapper;
import com.home.knowbase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "userCache", key = "#token", unless = "#result == null")
    public Optional<UserDTO> getUserByToken(UUID token) {
        return userRepository.findUserByToken(token).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserByLogin(String login) {
        return userRepository.findUserIdByLogin(login).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public User getUserByIdOrThrow(Long id) {
        return getUserById(id)
                .orElseThrow(RuntimeException::new);
    }

    private Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
