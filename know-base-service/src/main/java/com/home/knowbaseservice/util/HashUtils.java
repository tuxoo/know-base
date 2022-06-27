package com.home.knowbaseservice.util;

import com.home.knowbaseservice.model.exception.HashException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private static final MessageDigest mdSHA1;
    private static final String SHA1 = "SHA-1";

    static {
        try {
            mdSHA1 = MessageDigest.getInstance(SHA1);
        } catch (NoSuchAlgorithmException e) {
            throw new HashException(e);
        }
    }

    private HashUtils() {
    }

    public static String hashSHA1(String password) {
        byte[] bytes = mdSHA1.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));

        return sb.toString();
    }
}
