package com.landonthull.quotemaster.auth;

import com.landonthull.quotemaster.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetail userDetail;
  private final HandlerExceptionResolver handlerExceptionResolver;

  public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserDetail userDetail,
      HandlerExceptionResolver handlerExceptionResolver) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetail = userDetail;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    if (
        !StringUtils.hasLength(authHeader) ||
            !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")
    ) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      jwt = authHeader.substring(7);
      userEmail = jwtTokenUtil.extractEmail(jwt);
      if (StringUtils.hasLength(userEmail) &&
          SecurityContextHolder.getContext().getAuthentication() == null
      ) {
        UserDetails userDetails = userDetail.loadUserByUsername(userEmail);
        if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
          SecurityContext context = SecurityContextHolder.createEmptyContext();
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          context.setAuthentication(authToken);
          SecurityContextHolder.setContext(context);
        }
      }
    } catch (ExpiredJwtException ex) {
      logger.info("Invalid JWT credentials, rejecting request");

      handlerExceptionResolver.resolveException(request, response, null, ex);

      return;
    }

    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      logger.warn("Exception encountered in filter chain", ex);
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return "/login".equals(path);
  }
}
