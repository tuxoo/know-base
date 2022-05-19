package com.home.knowbase.config;

import com.home.knowbase.properties.ApplicationProperty;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final ApplicationProperty applicationProperty;

    @Bean
    public OpenAPI openApi() {
        OpenAPI openAPI = new OpenAPI().components(new Components());
        if (StringUtils.isNoneBlank(applicationProperty.url())) {
            openAPI.servers(List.of(new Server().url(applicationProperty.url() + applicationProperty.apiPath())));
        }
        return openAPI;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("common")
                .pathsToMatch("/util/**")
                .build();
    }
}
