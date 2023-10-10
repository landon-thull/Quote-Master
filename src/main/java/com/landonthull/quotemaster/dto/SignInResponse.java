package com.landonthull.quotemaster.dto;

public class SignInResponse {

  private final String token;

  public SignInResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
