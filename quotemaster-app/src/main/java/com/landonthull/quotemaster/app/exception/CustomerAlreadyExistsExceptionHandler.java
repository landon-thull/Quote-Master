package com.landonthull.quotemaster.app.exception;

import com.landonthull.quotemaster.core.customer.domain.exception.CustomerAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerAlreadyExistsExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(
      CustomerAlreadyExistsExceptionHandler.class
  );

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(CustomerAlreadyExistsException.class)
  public String handleException(CustomerAlreadyExistsException ex) {
    logger.info(
        "REST Controller invoked the CustomerAlreadyExistsException handler for customer '{}'",
        ex.getMessage()
    );

    return String.format("Customer already exists with name '%s'", ex.getMessage());
  }
}
