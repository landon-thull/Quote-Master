package com.landonthull.quotemaster.core.customer.domain.exception;

public class CustomerValidationException extends RuntimeException {

  public CustomerValidationException(final String message) {
    super(message);
  }
}
