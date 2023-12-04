package com.landonthull.quotemaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerQuotesInfoDto {

  private long total;
  private long open;
  private long quoted;
  @JsonProperty("no_quote")
  private long noQuote;
  private long cancelled;

  public CustomerQuotesInfoDto(long total, long open, long quoted, long noQuote, long cancelled) {
    this.total = total;
    this.open = open;
    this.quoted = quoted;
    this.noQuote = noQuote;
    this.cancelled = cancelled;
  }

  public CustomerQuotesInfoDto() {
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getOpen() {
    return open;
  }

  public void setOpen(long open) {
    this.open = open;
  }

  public long getQuoted() {
    return quoted;
  }

  public void setQuoted(long quoted) {
    this.quoted = quoted;
  }

  public long getNoQuote() {
    return noQuote;
  }

  public void setNoQuote(long noQuote) {
    this.noQuote = noQuote;
  }

  public long getCancelled() {
    return cancelled;
  }

  public void setCancelled(long cancelled) {
    this.cancelled = cancelled;
  }
}

