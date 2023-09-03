package com.landonthull.quotemaster.core.user.usecase.createuser;

import java.sql.Timestamp;
import java.util.UUID;

public class CreateUserResponse {
  private UUID id;
  private String email;
  private Timestamp createdAt;

  public CreateUserResponse() {
  }

  public CreateUserResponse(UUID id, String email, Timestamp createdAt) {
    this.id = id;
    this.email = email;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
