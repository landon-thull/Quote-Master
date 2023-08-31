package com.landonthull.quotemaster.core.unit.user.usercase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.landonthull.quotemaster.core.user.domain.exception.UserValidationException;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUser;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import com.landonthull.quotemaster.core.user.usecase.validator.UserValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {

  private static CreateUserRequest request;

  @BeforeAll
  public static void setup() {
    request = new CreateUserRequest(
        "testemail@email.com",
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );
  }

  @Test
  public void validateCreateUserRequest_RequestValid_NoExceptionThrown() {
    assertDoesNotThrow(() -> UserValidator.validateCreateUserRequest(request));
  }

  @Test
  public void validateCreateUserRequest_FieldNull_ThrowUserValidationException() {
    CreateUserRequest badRequest = new CreateUserRequest(
        null,
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );

    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setEmail("email@email.com");
    badRequest.setPassword(null);
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setPassword("mypassword123");
    badRequest.setFirstName(null);
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setFirstName("MyFirstName");
    badRequest.setLastName(null);
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );
  }

  @Test
  public void validateCreateUserRequest_FieldBlank_ThrowValidationException() {
    CreateUserRequest badRequest = new CreateUserRequest(
        "",
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );

    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setEmail("email@email.com");
    badRequest.setPassword("");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setPassword("mypassword123");
    badRequest.setFirstName("");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setFirstName("MyFirstName");
    badRequest.setLastName("");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );
  }

  @Test
  public void validateCreateUserRequest_EmailInvalid_ThrowUserValidationException() {
    CreateUserRequest badEmailRequest = new CreateUserRequest(
        "",
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("@email.com");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademailATemail.com");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademail@emailcom");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademail@.com");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("@email.com");
    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );
  }

  @Test
  public void validateCreateUserRequest_PasswordShort_ThrowUserValidationError() {
    CreateUserRequest badRequest = new CreateUserRequest(
        "email@email.com",
        "short",
        "MyFirstName",
        "MyLastName"
    );

    assertThrows(
        UserValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );
  }
}
