package com.landonthull.quotemaster.util;

import com.landonthull.quotemaster.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  @Value("${security.jwt-token-validity}")
  private long JWT_TOKEN_VALIDITY;

  @Value("${security.jwt-secret-key}")
  private String SECRET_KEY;

  public void logValues() {
    System.out.println(JWT_TOKEN_VALIDITY);
    System.out.println(SECRET_KEY);
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String email = extractClaim(token, Claims::getSubject);
    return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(user.getEmail())
        .setIssuer("QuoteMaster")
        .claim("role", user.getRole().toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  private boolean isTokenExpired(String token) {
    Date expiration = extractClaim(token, Claims::getExpiration);
    return expiration.before(new Date());
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimResolvers.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }
}
