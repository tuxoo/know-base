package com.home.knowbaseservice.util;

import com.home.knowbaseservice.model.exception.HashException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class HashService {

    @Value("${app.salt}")
    private String salt;
    private final String SHA1 = "SHA-1";
    private MessageDigest mdSHA1;

    @PostConstruct
    private void createDigest() {
        try {
            mdSHA1 = MessageDigest.getInstance(SHA1);
        } catch (NoSuchAlgorithmException e) {
            throw new HashException(e);
        }
    }

    public String hashSHA1(String password) {
        mdSHA1.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = mdSHA1.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));

        return sb.toString();
    }
}
