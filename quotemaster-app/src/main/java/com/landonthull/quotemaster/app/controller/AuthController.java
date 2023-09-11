package com.landonthull.quotemaster.app.controller;

import com.landonthull.quotemaster.app.auth.AuthService;
import com.landonthull.quotemaster.app.dto.SignInRequest;
import com.landonthull.quotemaster.app.dto.SignInResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public SignInResponse login(@Valid @RequestBody SignInRequest request) {
    return authService.signIn(request);
  }
}
