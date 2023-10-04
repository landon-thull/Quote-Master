package com.landonthull.quotemaster.app.dto;

import com.landonthull.quotemaster.core.user.domain.entity.UserRole;
import java.util.UUID;

public class GetCurrentUserResponse {

  private UUID id;
  private String email;
  private UserRole role;
  private String lastName;
  private String firstName;

  public GetCurrentUserResponse(UUID id, String email, UserRole role, String lastName, String firstName) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public GetCurrentUserResponse() {
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

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
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
}
