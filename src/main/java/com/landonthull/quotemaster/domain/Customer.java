package com.landonthull.quotemaster.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Customer name is required")
  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "notes")
  private String notes;

  @NotBlank(message = "Customer industry is required")
  @Column(name = "industry")
  private String industry;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "customer_id", fetch = FetchType.LAZY)
  private List<Quote> quotes = new ArrayList<>();

  @PrePersist
  private void onCreate() {
    this.createdAt = new Timestamp(System.currentTimeMillis());
  }

  @PreUpdate
  private void onUpdate() {
    this.updatedAt = new Timestamp(System.currentTimeMillis());
  }

  public Customer(Long id, String name, String notes, String industry, Timestamp createdAt,
      Timestamp updatedAt, List<Quote> quotes) {
    this.id = id;
    this.name = name;
    this.notes = notes;
    this.industry = industry;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.quotes = quotes;
  }

  public Customer(String name, String notes, String industry) {
    this.name = name;
    this.notes = notes;
    this.industry = industry;
  }

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public List<Quote> getQuotes() {
    return quotes;
  }

  public void setQuotes(List<Quote> quotes) {
    this.quotes = quotes;
  }
}
