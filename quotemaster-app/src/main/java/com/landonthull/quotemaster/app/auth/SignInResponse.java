package com.landonthull.quotemaster.app.auth;

public class SignInResponse {

  private final String token;

  public SignInResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
