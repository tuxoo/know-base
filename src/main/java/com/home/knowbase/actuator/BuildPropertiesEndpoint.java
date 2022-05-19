package com.home.knowbase.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "build-property")
@RequiredArgsConstructor
public class BuildPropertiesEndpoint {

    private final BuildProperties buildProperties;

    @ReadOperation
    public BuildProperties version() {
        return buildProperties;
    }
}
