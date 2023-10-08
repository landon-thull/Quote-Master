package com.landonthull.quotemaster.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

  public ResourceAlreadyExistsException(String entity, String field, String value) {
    super(String.format("%s already exists with %s '%s'", entity, field, value));
  }
}
