package com.home.knowbase.config;

import com.home.knowbase.security.CustomTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final CustomTokenAuthenticationFilter customTokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/auth").permitAll()
                .anyRequest()
                .authenticated();
//                .and()
//                .addFilterBefore(customTokenAuthenticationFilter, AppAuthenticationFilter.class)

//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
    }
}
