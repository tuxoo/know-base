package com.home.knowbase.controller;

import com.home.knowbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping
    public String getTest() {
        userService.getUserByIdOrThrow(1L);
        userService.getUserByIdOrThrow(2L);
        return "ping";
    }
}
