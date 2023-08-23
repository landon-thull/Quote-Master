package com.landonthull.quotemaster.core.domain.entity;

import java.sql.Timestamp;
import java.util.UUID;

public class User {

  private UUID id;
  private String email;
  private String password;
  private boolean isActive;
  // TODO add authority
  private String lastName;
  private String firstName;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public User() {
  }

  public User(UUID id, String email, String password, boolean isActive, String lastName,
      String firstName, Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.isActive = isActive;
    this.lastName = lastName;
    this.firstName = firstName;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
