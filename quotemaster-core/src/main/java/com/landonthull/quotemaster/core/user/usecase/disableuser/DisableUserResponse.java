package com.landonthull.quotemaster.core.user.usecase.disableuser;

public class DisableUserResponse {

  private String message;

  public DisableUserResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
