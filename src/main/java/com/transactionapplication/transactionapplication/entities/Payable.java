package com.transactionapplication.transactionapplication.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "payables")
public class Payable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "status_payable_id", nullable = false)
  private StatusPayable statusPayable;

  @Column(name = "payment_date", nullable = false)
  private LocalDate paymentDate;

  @Column(name = "value_to_receive")
  private BigDecimal valueToReceive;

  public Payable() {}

  public Payable(StatusPayable status, LocalDate paymentDate, BigDecimal value) {
    this.statusPayable = status;
    this.paymentDate = paymentDate;
    this.valueToReceive = value;
  }

  public Long getId() {
    return id;
  }

  public StatusPayable getStatusPayable() {
    return statusPayable;
  }

  public void setStatusPayable(StatusPayable status) {
    this.statusPayable = status;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public BigDecimal getValueToReceive() {
    return valueToReceive;
  }

  public void setValueToReceive(BigDecimal value) {
    this.valueToReceive = value;
  }

}
