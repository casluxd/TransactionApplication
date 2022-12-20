package com.transactionapplication.transactionapplication.enums;

public enum StatusPayableEnum {

  PAID(1), WAITING_FUNDS(2);

  private final int value;

  StatusPayableEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
