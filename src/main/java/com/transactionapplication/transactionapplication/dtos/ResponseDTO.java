package com.transactionapplication.transactionapplication.dtos;

public class ResponseDTO<Detail> {

  private String title;
  private Integer status;
  private Detail detail;

  public ResponseDTO(String title, Integer status, Detail detail) {
    this.title = title;
    this.status = status;
    this.detail = detail;
  }

  public String getTitle() {
    return title;
  }

  public Integer getStatus() {
    return status;
  }

  public Detail getDetail() {
    return detail;
  }

}
