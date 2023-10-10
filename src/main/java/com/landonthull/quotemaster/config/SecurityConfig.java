package com.landonthull.quotemaster.config;

import com.landonthull.quotemaster.auth.JwtAuthenticationFilter;
import com.landonthull.quotemaster.auth.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final UserDetail userDetail;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(UserDetail userDetail, JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.userDetail = userDetail;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(CsrfConfigurer::disable)
        .cors(CorsConfigurer::disable)
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authenticationProvider()).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
        )
        .exceptionHandling((ex) -> ex
            .accessDeniedHandler(accessDeniedHandler())
        )
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/login", "/error", "/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return ((request, response, accessDeniedException) -> {
      response.sendError(HttpStatus.FORBIDDEN.value());
    });
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetail);
    authProvider.setPasswordEncoder(bCryptPasswordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager
  authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
