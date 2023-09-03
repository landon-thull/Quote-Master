package com.landonthull.quotemaster.core.common.domain.exception;

public class RequestValidationException extends RuntimeException {
  public RequestValidationException(String message) {
    super(message);
  }
}
