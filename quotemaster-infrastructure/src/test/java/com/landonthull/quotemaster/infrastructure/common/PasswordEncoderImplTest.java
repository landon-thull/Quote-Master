package com.landonthull.quotemaster.infrastructure.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.security.SecureRandom;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImplTest {

  // low strength to reduce test runtime
  private final BCryptPasswordEncoder bCryptPasswordEncoder =
      new BCryptPasswordEncoder(5, new SecureRandom());
  private final PasswordEncoderImpl passwordEncoder =
      new PasswordEncoderImpl(bCryptPasswordEncoder);

  @Test
  public void encode_hashNotPassword() {
    String password = "password";
    String hashed = passwordEncoder.encode(password);

    assertNotEquals(password, hashed);
  }
}
