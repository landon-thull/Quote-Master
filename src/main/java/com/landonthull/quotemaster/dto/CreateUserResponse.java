package com.landonthull.quotemaster.dto;

import java.sql.Timestamp;

public class CreateUserResponse {

  private Long id;
  private String email;
  private Timestamp createdAt;

  public CreateUserResponse(Long id, String email, Timestamp createdAt) {
    this.id = id;
    this.email = email;
    this.createdAt = createdAt;
  }

  public CreateUserResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
