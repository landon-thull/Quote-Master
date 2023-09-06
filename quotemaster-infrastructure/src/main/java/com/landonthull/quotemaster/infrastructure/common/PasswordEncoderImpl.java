package com.landonthull.quotemaster.infrastructure.common;

import com.landonthull.quotemaster.core.user.port.in.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public PasswordEncoderImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public String encode(final String password) {
    return bCryptPasswordEncoder.encode(password);
  }
}
