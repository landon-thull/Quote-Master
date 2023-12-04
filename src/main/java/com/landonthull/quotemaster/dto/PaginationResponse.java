package com.landonthull.quotemaster.dto;

import java.util.List;

public class PaginationResponse<T> {

  private List<T> data;
  private PaginationInfo pagination;

  public PaginationResponse(List<T> data, PaginationInfo pagination) {
    this.data = data;
    this.pagination = pagination;
  }

  public List<T> getData() {
    return this.data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public PaginationInfo getPagination() {
    return this.pagination;
  }

  public void setPagination(PaginationInfo pagination) {
    this.pagination = pagination;
  }
}
