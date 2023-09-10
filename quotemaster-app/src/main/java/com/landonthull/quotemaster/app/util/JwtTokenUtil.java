package com.landonthull.quotemaster.app.util;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

  private static final String SECRET_KEY = "75fe923aac138864c9afdd2def611781e170d2bf294c6f998d850ad78b2fb02e";

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
