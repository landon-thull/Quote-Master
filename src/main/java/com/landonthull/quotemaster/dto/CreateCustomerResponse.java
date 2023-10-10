package com.landonthull.quotemaster.dto;

import java.sql.Timestamp;

public class CreateCustomerResponse {

  private Long id;
  private Timestamp createdAt;

  public CreateCustomerResponse(Long id, Timestamp createdAt) {
    this.id = id;
    this.createdAt = createdAt;
  }

  public CreateCustomerResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
