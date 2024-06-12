package com.example.products.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtRolesConverter());

        return jwtAuthenticationConverter;
    }
}
