package com.home.knowbase.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Auth {
    AUTHORIZATION("Authorization", 13),
    BEARER("Bearer ", 7);

    private final String meaning;
    private final int length;
}
