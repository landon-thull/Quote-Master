package com.landonthull.quotemaster.app.auth;

import com.landonthull.quotemaster.app.service.UserDetail;
import com.landonthull.quotemaster.app.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenUtil jwtTokenUtil;
  private final UserDetail userDetail;

  public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,
      UserDetail userDetail) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetail = userDetail;
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

    jwt = authHeader.substring(7);
    userEmail = jwtTokenUtil.extractEmail(jwt);
    if (StringUtils.hasLength(userEmail) &&
        SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      UserDetails userDetails = userDetail.loadUserByUsername(userEmail);
      System.out.println(userDetails.getAuthorities());
      if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }
    filterChain.doFilter(request, response);
  }
}
