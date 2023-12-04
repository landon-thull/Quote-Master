package com.landonthull.quotemaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.landonthull.quotemaster.domain.Customer;
import java.sql.Timestamp;

public class CustomerDto {

  private Long id;
  private String name;
  private String notes;
  private String industry;
  @JsonProperty("created_at")
  private Timestamp createdAt;
  @JsonProperty("updated_at")
  private Timestamp updatedAt;
  private CustomerQuotesInfoDto quotes;

  public CustomerDto(Long id, String name, String notes, String industry, Timestamp createdAt,
      Timestamp updatedAt, CustomerQuotesInfoDto quotes) {
    this.id = id;
    this.name = name;
    this.notes = notes;
    this.industry = industry;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.quotes = quotes;
  }

  public CustomerDto(Customer customer, CustomerQuotesInfoDto quotes) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.notes = customer.getNotes();
    this.industry = customer.getIndustry();
    this.createdAt = customer.getCreatedAt();
    this.updatedAt = customer.getUpdatedAt();
    this.quotes = quotes;
  }

  public CustomerDto() {
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

  public CustomerQuotesInfoDto getQuotes() {
    return quotes;
  }

  public void setQuotes(CustomerQuotesInfoDto quotes) {
    this.quotes = quotes;
  }
}