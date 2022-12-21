package com.transactionapplication.transactionapplication.dtos;

public class ResponseDTO<T> {

  private static final int DEFAULT_STATUS = 200;

  private String title;
  private Integer status;
  private T detail;

  public ResponseDTO(String title, int status, T detail) {
    this.title = title;
    this.status = status;
    this.detail = detail;
  }

  public ResponseDTO(String title, T detail) {
    this(title, DEFAULT_STATUS, detail);
  }

  public String getTitle() {
    return title;
  }

  public Integer getStatus() {
    return status;
  }

  public T getDetail() {
    return detail;
  }

}
