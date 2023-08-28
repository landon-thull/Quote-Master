package com.landonthull.quotemaster.core.user.domain.exception;

public class UserValidationException extends RuntimeException {

  public UserValidationException(final String message) {
    super(message);
  }
}
