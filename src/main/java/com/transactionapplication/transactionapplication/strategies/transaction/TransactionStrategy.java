package com.transactionapplication.transactionapplication.strategies.transaction;

import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.entities.Transaction;

public interface TransactionStrategy {

  public boolean check(String paymentMethodName);
  
  public ResponseDTO<Transaction> processTransaction(Transaction transaction);

}
