package com.landonthull.quotemaster.infrastructure.common;

import com.landonthull.quotemaster.core.user.port.in.PasswordEncoder;
import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public PasswordEncoderImpl() {
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
  }

  @Override
  public String encode(final String password) {
    return bCryptPasswordEncoder.encode(password);
  }
}
