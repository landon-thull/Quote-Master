package com.landonthull.quotemaster.controller;

import com.landonthull.quotemaster.dto.SignInRequest;
import com.landonthull.quotemaster.dto.SignInResponse;
import com.landonthull.quotemaster.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  @Operation(summary = "Login", description = "Login with username and password.")
  public SignInResponse login(@Valid @RequestBody SignInRequest request) {
    return authService.signIn(request);
  }
}
