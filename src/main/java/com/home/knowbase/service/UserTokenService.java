package com.home.knowbase.service;

import com.home.knowbase.dto.UserTokenDTO;
import com.home.knowbase.entity.User;
import com.home.knowbase.entity.UserToken;
import com.home.knowbase.mapper.UserTokenMapper;
import com.home.knowbase.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTokenService {

    private final UserService userService;
    private final UserTokenRepository userTokenRepository;
    private final UserTokenMapper userTokenMapper;

    @Transactional(readOnly = true)
    public Optional<UserTokenDTO> getTokenByUserId(Long userId) {
        return userTokenRepository.findUserTokenByUserId(userId).map(userTokenMapper::toDTO);
    }

    @Transactional
    public UserTokenDTO putNewToken(Long userId) {
        User user = userService.getUserByIdOrThrow(userId);

        UserToken newToken = new UserToken();
        newToken.setToken(UUID.randomUUID());
        newToken.setExpiresAt(Instant.now().plus(Duration.ofHours(4)));
        newToken.setUser(user);

        return userTokenMapper
                .toDTO(userTokenRepository.save(newToken));
    }
}
