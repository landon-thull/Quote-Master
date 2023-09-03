package com.landonthull.quotemaster.core.user.usecase.validator;

import com.landonthull.quotemaster.core.common.domain.exception.RequestValidationException;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserRequest;
import java.util.regex.Pattern;

public class UserValidator {

  public static void validateCreateUserRequest(final CreateUserRequest request) {
    if (request == null) {
      throw new RequestValidationException("Create user request should not be null");
    }
    if (request.getEmail() == null || request.getEmail().isBlank()) {
      throw new RequestValidationException("Email is required");
    }
    if (request.getPassword() == null || request.getPassword().isBlank()) {
      throw new RequestValidationException("Password is required");
    }
    if (request.getFirstName() == null ||request.getFirstName().isBlank()) {
      throw new RequestValidationException("First name is required");
    }
    if (request.getLastName() == null || request.getLastName().isBlank()) {
      throw new RequestValidationException("Last name is required");
    }

    if (request.getPassword().length() < 8) {
      throw new RequestValidationException("Password should be at least 8 characters");
    }
    if (!emailIsValid(request.getEmail())) {
      throw new RequestValidationException("Email is invalid");
    }
  }

  public static void validateDisableUserRequest(DisableUserRequest request) {
    if (request == null) {
      throw new RequestValidationException("Request should not be null");
    }
    if (request.getUserId() == null) {
      throw new RequestValidationException("User ID is required");
    }
  }


  private static boolean emailIsValid(String email) {
    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)"
        + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    return Pattern.compile(regexPattern)
        .matcher(email)
        .matches();
  }
}
