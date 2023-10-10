package com.landonthull.quotemaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCustomerRequest {

  @NotBlank(message = "Customer name is required")
  private String name;

  private String notes;

  @NotBlank(message = "Customer industry is required")
  @Size(max = 64, message = "Customer industry must be 64 characters or shorter")
  private String industry;

  public CreateCustomerRequest(String name, String notes, String industry) {
    this.name = name;
    this.notes = notes;
    this.industry = industry;
  }

  public CreateCustomerRequest() {
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
}
