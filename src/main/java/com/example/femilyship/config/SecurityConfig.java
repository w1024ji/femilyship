package com.example.femilyship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // We need to authorize HTTP requests.
                .authorizeHttpRequests(auth -> auth
                        // This line specifically allows all requests to the H2 console path.
                        .requestMatchers("/h2-console/**").permitAll()
                        // For now, we will permit all other requests as well. We'll secure them later.
                        .anyRequest().permitAll()
                )
                // The H2 console runs in a frame, so we need to disable this security feature using the new syntax.
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // We also need to disable CSRF protection for the H2 console.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        return http.build();
    }
}

