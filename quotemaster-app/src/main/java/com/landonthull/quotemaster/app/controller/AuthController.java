package com.landonthull.quotemaster.app.controller;

import com.landonthull.quotemaster.app.auth.AuthService;
import com.landonthull.quotemaster.app.dto.SignInRequest;
import com.landonthull.quotemaster.app.dto.SignInResponse;
import com.landonthull.quotemaster.app.util.LoggingUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthController {
  private final Logger logger = LoggerFactory.getLogger(AuthController.class);

  private final AuthService authService;
  private final LoggingUtil loggingUtil;

  public AuthController(AuthService authService, LoggingUtil loggingUtil) {
    this.authService = authService;
    this.loggingUtil = loggingUtil;
  }

  @PostMapping("/login")
  public SignInResponse login(@Valid @RequestBody SignInRequest request) {
    logger.info("Login request initiated by user '{}'", loggingUtil.getPrincipalEmail());
    return authService.signIn(request);
  }
}
