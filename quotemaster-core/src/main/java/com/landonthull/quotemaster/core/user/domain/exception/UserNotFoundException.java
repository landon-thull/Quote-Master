package com.landonthull.quotemaster.core.user.domain.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(final String identifier) {
    super(String.format("User not found with identifier '%s'", identifier));
  }
}
