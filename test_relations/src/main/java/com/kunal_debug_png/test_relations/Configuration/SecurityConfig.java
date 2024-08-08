package com.kunal_debug_png.test_relations.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        // Allow unrestricted access to /stocks
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(Customizer.withDefaults()) // Use default settings for form login
                .httpBasic(Customizer.withDefaults()) // Use default settings for HTTP Basic authentication
                .build();
    }
}
