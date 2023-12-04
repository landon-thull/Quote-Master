package com.landonthull.quotemaster.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(
    name = "quote",
    indexes = @Index(name = "customer_id_index", columnList = "customer_id")
)
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @Column(name = "part_number")
  private String partNumber;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "status", nullable = false)
  private QuoteStatus status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "requested_on", nullable = false)
  private Date requestedOn;

  @Column(name = "effective_on")
  private Date effectiveOn;

  @Column(name = "expires_on")
  private Date expiresOn;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @PrePersist
  private void onCreate() {
    this.createdAt = new Timestamp(System.currentTimeMillis());
  }

  @PreUpdate
  private void onUpdate() {
    this.updatedAt = new Timestamp(System.currentTimeMillis());
  }

  public Quote(Long id, String partNumber, QuoteStatus status, Customer customer, Date requestedOn, Date effectiveOn,
      Date expiresOn, Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.partNumber = partNumber;
    this.status = status;
    this.customer = customer;
    this.requestedOn = requestedOn;
    this.effectiveOn = effectiveOn;
    this.expiresOn = expiresOn;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Quote(QuoteStatus status, Customer customer, Date requestedOn) {
    this.status = status;
    this.customer = customer;
    this.requestedOn = requestedOn;
  }

  public Quote() {
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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
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
