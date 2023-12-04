package com.landonthull.quotemaster.dto;

public class PaginationInfo {

  private Long total_records;
  private Integer current_page;
  private Integer total_pages;
  private Integer next_page;
  private Integer prev_page;

  public PaginationInfo(
      Long total_records, Integer current_page, Integer total_pages, Integer next_page, Integer prev_page
  ) {
    this.total_records = total_records;
    this.current_page = current_page;
    this.total_pages = total_pages;
    this.next_page = next_page;
    this.prev_page = prev_page;
  }

  public PaginationInfo() {

  }

  public Long getTotal_records() {
    return total_records;
  }

  public void setTotal_records(Long total_records) {
    this.total_records = total_records;
  }

  public Integer getCurrent_page() {
    return current_page;
  }

  public void setCurrent_page(Integer current_page) {
    this.current_page = current_page;
  }

  public Integer getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(Integer total_pages) {
    this.total_pages = total_pages;
  }

  public Integer getNext_page() {
    return next_page;
  }

  public void setNext_page(Integer next_page) {
    this.next_page = next_page;
  }

  public Integer getPrev_page() {
    return prev_page;
  }

  public void setPrev_page(Integer prev_page) {
    this.prev_page = prev_page;
  }
}
