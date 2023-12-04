package com.landonthull.quotemaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginationInfo {

  @JsonProperty("total_records")
  private Long totalRecords;
  @JsonProperty("current_page")
  private Integer currentPage;
  @JsonProperty("total_pages")
  private Integer totalPages;
  @JsonProperty("next_page")
  private Integer nextPage;
  @JsonProperty("prev_page")
  private Integer prevPage;

  public PaginationInfo(
      Long totalRecords, Integer currentPage, Integer totalPages, Integer nextPage, Integer prevPage
  ) {
    this.totalRecords = totalRecords;
    this.currentPage = currentPage;
    this.totalPages = totalPages;
    this.nextPage = nextPage;
    this.prevPage = prevPage;
  }

  public PaginationInfo() {

  }

  public Long getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(Long totalRecords) {
    this.totalRecords = totalRecords;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Integer getNextPage() {
    return nextPage;
  }

  public void setNextPage(Integer nextPage) {
    this.nextPage = nextPage;
  }

  public Integer getPrevPage() {
    return prevPage;
  }

  public void setPrevPage(Integer prevPage) {
    this.prevPage = prevPage;
  }
}
