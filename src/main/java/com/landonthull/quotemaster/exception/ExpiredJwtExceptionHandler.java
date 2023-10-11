package com.landonthull.quotemaster.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpiredJwtExceptionHandler {

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<String> handleCredentialsExpiredException(ExpiredJwtException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
