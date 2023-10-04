package com.landonthull.quotemaster.core.customer.usecase.createcustomer;

public class CreateCustomerRequest {

  private String name;
  private String notes;
  private String industry;

  public CreateCustomerRequest() {
  }

  public CreateCustomerRequest(String name, String notes, String industry) {
    this.name = name;
    this.notes = notes;
    this.industry = industry;
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
