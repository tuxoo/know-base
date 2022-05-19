package com.home.knowbase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {

    private final BuildProperties buildProperties;

    @GetMapping("/version")
    public String version() {
        return buildProperties.getVersion();
    }
}
