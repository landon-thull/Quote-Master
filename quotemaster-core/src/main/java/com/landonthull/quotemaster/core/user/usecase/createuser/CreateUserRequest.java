package com.landonthull.quotemaster.core.user.usecase.createuser;

import com.landonthull.quotemaster.core.user.domain.entity.UserRole;

public class CreateUserRequest {

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private UserRole userRole;

  public CreateUserRequest() {
  }

  public CreateUserRequest(String email, String password, String firstName, String lastName,
      UserRole userRole) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userRole = userRole;
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }
}
