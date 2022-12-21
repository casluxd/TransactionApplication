package com.transactionapplication.transactionapplication.dtos;

import java.util.List;

import com.transactionapplication.transactionapplication.entities.Payable;

public class PayablesDTO {
  List<Payable> available;
  List<Payable> waitingFunds;

  public PayablesDTO(List<Payable> available, List<Payable> waitingFunds) {
    this.available = available;
    this.waitingFunds = waitingFunds;
  }

  public List<Payable> getAvailable() {
    return available;
  }

  public List<Payable> getWaitingFunds() {
    return waitingFunds;
  }

  
}
