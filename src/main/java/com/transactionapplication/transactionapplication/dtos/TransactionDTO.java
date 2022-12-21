package com.transactionapplication.transactionapplication.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO {

  private BigDecimal amount;

  private String description;

  @JsonProperty(value = "payment_method_id", required = true)
  private Long paymentMethodId;

  @JsonProperty(value = "last_four_digits_card_number", required = true)
  private String lastFourDigitsCardNumber;

  @JsonProperty(value = "card_owner_name", required = true)
  private String cardOwnerName;

  @JsonFormat(pattern = "MM/yyyy")
  @JsonProperty(value = "card_validate_date", required = true)
  private String cardValidateDate;
  
  @JsonProperty(value = "card_verification_code", required = true)
  private String cardVerificationCode;

  /**
   * @param amount
   * @param paymentMethod
   * @param lastFourDigitsCardNumber
   * @param cardOwnerName
   * @param cardValidateDate
   * @param cardVerificationCode
   */
  public TransactionDTO(
      BigDecimal amount,
      String description,
      Long paymentMethodId,
      String lastFourDigitsCardNumber,
      String cardOwnerName,
      String cardValidateDate,
      String cardVerificationCode) {
    this.amount = amount;
    this.description = description;
    this.paymentMethodId = paymentMethodId;
    this.lastFourDigitsCardNumber = lastFourDigitsCardNumber;
    this.cardOwnerName = cardOwnerName;
    this.cardValidateDate = cardValidateDate;
    this.cardVerificationCode = cardVerificationCode;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  public Long getPaymentMethodId() {
    return paymentMethodId;
  }

  public String getLastFourDigitsCardNumber() {
    return lastFourDigitsCardNumber;
  }

  public String getCardOwnerName() {
    return cardOwnerName;
  }

  public String getCardValidateDate() {
    return cardValidateDate;
  }

  public String getCardVerificationCode() {
    return cardVerificationCode;
  }

}
