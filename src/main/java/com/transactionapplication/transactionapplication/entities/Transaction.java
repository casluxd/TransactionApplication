package com.transactionapplication.transactionapplication.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal amount;

  private String description;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", nullable = false)
  private PaymentMethod paymentMethod;

  @Column(name = "last_four_digits_card_number")
  private String lastFourDigitsCardNumber;

  @Column(name = "card_owner_name")
  private String cardOwnerName;

  @Column(name = "card_validate_data")
  @DateTimeFormat(pattern = "dd/MM/yy")
  private LocalDate cardValidateDate;

  @Column(name = "card_verification_code")
  private String cardVerificationCode;

  public Transaction() {
  }

  public Transaction(BigDecimal amount, String description, PaymentMethod paymentMethod,
      String lastFourDigitsCardNumber, String cardOwnerName, String cardValidateDate, String cardVerificationCode) {
    this.amount = amount;
    this.description = description;
    this.paymentMethod = paymentMethod;
    this.setLastFourDigitsCardNumber(lastFourDigitsCardNumber);
    this.cardOwnerName = cardOwnerName;
    setCardValidateDate(cardValidateDate);
    this.cardVerificationCode = cardVerificationCode;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getLastFourDigitsCardNumber() {
    return lastFourDigitsCardNumber;
  }

  public void setLastFourDigitsCardNumber(String lastFourDigitsCardNumber) {
    this.lastFourDigitsCardNumber = "XXXX-XXXX-XXXX-" + lastFourDigitsCardNumber;
  }

  public String getCardOwnerName() {
    return cardOwnerName;
  }

  public void setCardOwnerName(String cardOwnerName) {
    this.cardOwnerName = cardOwnerName;
  }

  public LocalDate getCardValidateDate() {
    return cardValidateDate;
  }

  public void setCardValidateDate(String cardValidateDate) {
    String[] monthYear = cardValidateDate.split("/");
    this.cardValidateDate = LocalDate.parse(monthYear[1] + "/" + monthYear[0] + "/01",
        DateTimeFormatter.ofPattern("yyyy/MM/dd"));
  }

  public String getCardVerificationCode() {
    return cardVerificationCode;
  }

  public void setCardVerificationCode(String cardVerificationCode) {
    this.cardVerificationCode = cardVerificationCode;
  }

}
