package com.landonthull.quotemaster.app.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.entity.UserRole;
import java.sql.Timestamp;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JwtTokenUtilTests {

  private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

  @Test
  public void generateAccessToken_EmailValid_ReturnToken() {
    User user = new User(
        UUID.randomUUID(),
        "email@email.com",
        "Password123",
        false,
        UserRole.SALES_REPRESENTATIVE,
        "LastName",
        "FirstName",
        new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis())
    );

    String token = jwtTokenUtil.generateAccessToken(user);

    user.setEmail("email2@email.com");

    String token2 = jwtTokenUtil.generateAccessToken(user);

    assertNotNull(token);
    assertNotNull(token2);
    assertNotEquals(token, token2);
  }
}
