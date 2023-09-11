package com.landonthull.quotemaster.app.dto;

import jakarta.validation.constraints.NotBlank;

public class SignInRequest {

  @NotBlank(message = "Email is required.")
  private final String email;

  @NotBlank(message = "Password is required.")
  private final String password;

  public SignInRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
