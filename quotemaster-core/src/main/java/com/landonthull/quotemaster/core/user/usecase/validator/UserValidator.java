package com.landonthull.quotemaster.core.user.usecase.validator;

import com.landonthull.quotemaster.core.user.domain.exception.UserValidationException;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import java.util.regex.Pattern;

public class UserValidator {

  public static void validateCreateUserRequest(final CreateUserRequest request) {
    if (request == null) {
      throw new UserValidationException("Create user request should not be null");
    }
    if (request.getEmail() == null || request.getEmail().isBlank()) {
      throw new UserValidationException("Email is required");
    }
    if (request.getPassword() == null || request.getPassword().isBlank()) {
      throw new UserValidationException("Password is required");
    }
    if (request.getFirstName() == null ||request.getFirstName().isBlank()) {
      throw new UserValidationException("First is required");
    }
    if (request.getLastName() == null || request.getLastName().isBlank()) {
      throw new UserValidationException("Last is required");
    }

    if (request.getPassword().length() < 8) {
      throw new UserValidationException("Password should be at least 8 characters");
    }
    if (!emailIsValid(request.getEmail())) {
      throw new UserValidationException("Email should be valid");
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
