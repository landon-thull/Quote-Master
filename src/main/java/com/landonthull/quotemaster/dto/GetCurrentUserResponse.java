package com.landonthull.quotemaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.landonthull.quotemaster.domain.UserRole;

public class GetCurrentUserResponse {

  private Long id;
  private String email;
  private UserRole role;
  @JsonProperty("last_name")
  private String lastName;
  @JsonProperty("first_name")
  private String firstName;

  public GetCurrentUserResponse(Long id, String email, UserRole role, String lastName,
      String firstName) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public GetCurrentUserResponse() {
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
