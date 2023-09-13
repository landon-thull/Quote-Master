package com.landonthull.quotemaster.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.landonthull.quotemaster.app.auth.AuthService;
import com.landonthull.quotemaster.app.dto.SignInRequest;
import com.landonthull.quotemaster.app.dto.SignInResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {

  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  @Test
  public void login_ValidCredentials_ReturnJwt() {
    when(authService.signIn(any(SignInRequest.class)))
        .thenReturn(new SignInResponse("ExampleJsonWebToken"));

    SignInRequest request = new SignInRequest("email@email.com", "Password123");


    SignInResponse response = authController.login(request);

    assertNotNull(response);
    assertEquals(response.getToken(), "ExampleJsonWebToken");
  }
}
