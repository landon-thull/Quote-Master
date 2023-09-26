package com.landonthull.quotemaster.core.customer.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
  public CustomerAlreadyExistsException(final String name) {
    super(name);
  }
}
