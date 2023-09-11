package com.landonthull.quotemaster.core.user.port.in;

public interface PasswordEncoder {

  String encode(String password);
}
