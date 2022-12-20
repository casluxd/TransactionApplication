package com.transactionapplication.transactionapplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "payment_method")
public class PaymentMethod {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "processing_fee")
  private Double processingFee;

  public PaymentMethod() {
  }

  public PaymentMethod(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getProcessingFee() {
    return processingFee;
  }

  public void setProcessingFee(Double processingFee) {
    this.processingFee = processingFee;
  }

  


}
