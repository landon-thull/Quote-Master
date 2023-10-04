package com.landonthull.quotemaster.infrastructure.postgres.customer;

import com.landonthull.quotemaster.core.customer.domain.entity.CustomerStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class JpaCustomerEntity {

  @Id
  private UUID id;

  private String name;

  private String notes;

  @Enumerated(EnumType.STRING)
  private CustomerStatus status;

  private String industry;

  private Timestamp createdAt;

  private Timestamp updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Timestamp(System.currentTimeMillis());
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Timestamp(System.currentTimeMillis());
  }

  public JpaCustomerEntity() {
  }

  public JpaCustomerEntity(UUID id, String name, String notes, CustomerStatus status,
      String industry,
      Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.name = name;
    this.notes = notes;
    this.status = status;
    this.industry = industry;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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
