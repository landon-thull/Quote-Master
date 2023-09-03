package com.landonthull.quotemaster.core.user.port;

public interface PasswordEncoder {

  String encode(String password);
}
