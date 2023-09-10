package com.landonthull.quotemaster.app.auth;

public class SignInRequest {

  private final String email;
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
