package com.landonthull.quotemaster.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(CsrfConfigurer::disable)
        .cors(CorsConfigurer::disable)
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/**", "/users", "/users/**").permitAll()
        );
    return http.build();
  }
}
