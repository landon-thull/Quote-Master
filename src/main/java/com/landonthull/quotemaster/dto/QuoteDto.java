package com.landonthull.quotemaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.domain.QuoteStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

public class QuoteDto {

  private Long id;
  @JsonProperty("part_number")
  private String partNumber;
  private QuoteStatus status;
  @JsonProperty("customer_id")
  private Long  customerId;
  @JsonProperty("requested_on")
  private Date requestedOn;
  @JsonProperty("effective_on")
  private Date effectiveOn;
  @JsonProperty("expires_on")
  private Date expiresOn;
  @JsonProperty("created_at")
  private Timestamp createdAt;
  @JsonProperty("updated_at")
  private Timestamp updatedAt;

  public QuoteDto(Quote quote) {
    this.id = quote.getId();
    this.partNumber = quote.getPartNumber();
    this.status = quote.getStatus();
    this.customerId = quote.getCustomer().getId();
    this.requestedOn = quote.getRequestedOn();
    this.effectiveOn = quote.getEffectiveOn();
    this.expiresOn = quote.getExpiresOn();
    this.createdAt = quote.getCreatedAt();
    this.updatedAt = quote.getUpdatedAt();
  }

  public QuoteDto(
    Long id, String partNumber, QuoteStatus status, Long customerId, Date requestedOn,
    Date effectiveOn, Date expiresOn, Timestamp createdAt, Timestamp updatedAt
  ) {
    this.id = id;
    this.partNumber = partNumber;
    this.status = status;
    this.customerId = customerId;
    this.requestedOn = requestedOn;
    this.effectiveOn = effectiveOn;
    this.expiresOn = expiresOn;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPartNumber() {
    return partNumber;
  }

  public void setPartNumber(String partNumber) {
    this.partNumber = partNumber;
  }

  public QuoteStatus getStatus() {
    return status;
  }

  public void setStatus(QuoteStatus status) {
    this.status = status;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Date getRequestedOn() {
    return requestedOn;
  }

  public void setRequestedOn(Date requestedOn) {
    this.requestedOn = requestedOn;
  }

  public Date getEffectiveOn() {
    return effectiveOn;
  }

  public void setEffectiveOn(Date effectiveOn) {
    this.effectiveOn = effectiveOn;
  }

  public Date getExpiresOn() {
    return expiresOn;
  }

  public void setExpiresOn(Date expiresOn) {
    this.expiresOn = expiresOn;
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
