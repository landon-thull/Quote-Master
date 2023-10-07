package com.landonthull.quotemaster.core.customer.domain.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String field, String value) {
    super(String.format("Customer not found with %s '%s'", field, value));
  }
}
