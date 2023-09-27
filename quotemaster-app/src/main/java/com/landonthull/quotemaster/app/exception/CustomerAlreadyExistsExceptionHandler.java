package com.landonthull.quotemaster.app.exception;

import com.landonthull.quotemaster.core.customer.domain.exception.CustomerAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerAlreadyExistsExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CustomerAlreadyExistsException.class)
  public Map<String, String> handleException(CustomerAlreadyExistsException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put(
        "name",
        String.format("Customer already exists with name '%s'", ex.getMessage()));
    return errors;
  }
}
