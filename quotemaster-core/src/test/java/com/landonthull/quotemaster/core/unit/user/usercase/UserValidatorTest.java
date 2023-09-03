package com.landonthull.quotemaster.core.unit.user.usercase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.landonthull.quotemaster.core.common.domain.exception.RequestValidationException;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserRequest;
import com.landonthull.quotemaster.core.user.usecase.validator.UserValidator;
import java.util.UUID;
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
  public void validateCreateUserRequest_FieldNull_ThrowRequestValidationException() {
    CreateUserRequest badRequest = new CreateUserRequest(
        null,
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );

    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setEmail("email@email.com");
    badRequest.setPassword(null);
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setPassword("mypassword123");
    badRequest.setFirstName(null);
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setFirstName("MyFirstName");
    badRequest.setLastName(null);
    assertThrows(
        RequestValidationException.class,
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
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setEmail("email@email.com");
    badRequest.setPassword("");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setPassword("mypassword123");
    badRequest.setFirstName("");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );

    badRequest.setFirstName("MyFirstName");
    badRequest.setLastName("");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );
  }

  @Test
  public void validateCreateUserRequest_EmailInvalid_ThrowRequestValidationException() {
    CreateUserRequest badEmailRequest = new CreateUserRequest(
        "",
        "mypassword123",
        "Myfirstname",
        "Mylastname"
    );
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("@email.com");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademailATemail.com");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademail@emailcom");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("bademail@.com");
    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badEmailRequest)
    );

    badEmailRequest.setEmail("@email.com");
    assertThrows(
        RequestValidationException.class,
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
        RequestValidationException.class,
        () -> UserValidator.validateCreateUserRequest(badRequest)
    );
  }

  @Test
  public void validateDisableUserRequest_RequestValid_NoExceptionThrown() {
    DisableUserRequest disableUserRequest = new DisableUserRequest(UUID.randomUUID());

    assertDoesNotThrow(() -> UserValidator.validateDisableUserRequest(disableUserRequest));
  }

  @Test
  public void validateDisableUserRequest_RequestNull_ThrowRequestValidationException() {
    DisableUserRequest disableUserRequest = null;

    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateDisableUserRequest(disableUserRequest)
    );
  }

  @Test
  public void validateDisableUserRequest_UuidNull_ThrowRequestValidationException() {
    DisableUserRequest disableUserRequest = new DisableUserRequest(null);

    assertThrows(
        RequestValidationException.class,
        () -> UserValidator.validateDisableUserRequest(disableUserRequest)
    );
  }
}
