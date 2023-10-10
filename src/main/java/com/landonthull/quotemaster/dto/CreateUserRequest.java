package com.landonthull.quotemaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be correctly formatted")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be 8 characters or longer")
  private String password;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @NotBlank(message = "First name is required")
  private String firstName;

  public CreateUserRequest(String email, String password, String lastName, String firstName) {
    this.email = email;
    this.password = password;
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public CreateUserRequest() {
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
