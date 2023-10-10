package com.landonthull.quotemaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignInRequest {

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be correctly formatted")
  private final String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be 8 characters or longer")
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
