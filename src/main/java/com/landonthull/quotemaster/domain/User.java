package com.landonthull.quotemaster.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(
    name = "app_user",
    indexes = @Index(name = "email_index", columnList = "email")
)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Email(message = "Email must be correctly formatted")
  @NotBlank(message = "Email is required")
  @Column(name = "email")
  private String email;
  
  @NotBlank
  @Size(min = 8, message = "Password must be 8 characters or longer")
  @Size(max = 32, message = "Password must be 32 characters or shorter")
  @Column(name = "password")
  private String password;

  @NotNull
  @Column(name = "active")
  private boolean isActive;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "User role is required")
  @Column(name = "role")
  private UserRole role;

  @NotBlank(message = "Last name is required")
  @Column(name = "last_name")
  private String lastName;

  @NotBlank(message = "First name is required")
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  // automatically set created_at field
  @PrePersist
  protected void onCreate() {
    createdAt = new Timestamp(System.currentTimeMillis());
  }

  // automatically update updated_at field
  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Timestamp(System.currentTimeMillis());
  }

  public User(Long id, String email, String password, boolean isActive, UserRole role, String lastName,
      String firstName, Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.isActive = isActive;
    this.role = role;
    this.lastName = lastName;
    this.firstName = firstName;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public User(String email, String password, String lastName, String firstName) {
    this.email = email;
    this.password = password;
    this.lastName = lastName;
    this.firstName = firstName;

    // default status of active
    this.isActive = true;

    // default role of SALES_REPRESENTATIVE
    this.role = UserRole.SALES_REPRESENTATIVE;
  }

  public User() {
    this.isActive = true;
    this.role = UserRole.SALES_REPRESENTATIVE;
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

  public String getPassword() {
    return this.password;
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
