package com.transactionapplication.transactionapplication.exceptions.transaction;

public class PaymentMethodInvalid extends RuntimeException {
  
  public PaymentMethodInvalid() {
    super("The payment method entered is invalid.");
  }

}
