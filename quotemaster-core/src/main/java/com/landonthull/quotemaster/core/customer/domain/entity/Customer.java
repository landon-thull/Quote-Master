package com.landonthull.quotemaster.core.customer.domain.entity;

import java.sql.Timestamp;
import java.util.UUID;

public class Customer {

  private UUID id;
  private String name;
  private String notes;
  private CustomerStatus status;
  private String industry;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public Customer() {
  }

  public Customer(UUID id, String name, String notes, String industry,
      Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.name = name;
    this.notes = notes;
    this.status = CustomerStatus.ACTIVE;
    this.industry = industry;
    this.createdAt = createdAt;
    this.createdAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public CustomerStatus getStatus() {
    return status;
  }

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }
}
