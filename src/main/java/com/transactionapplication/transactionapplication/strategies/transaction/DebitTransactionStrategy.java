package com.transactionapplication.transactionapplication.strategies.transaction;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.repositories.PayableRepository;
import com.transactionapplication.transactionapplication.repositories.StatusPayableRepository;

@Component
public class DebitTransactionStrategy extends AbstractTransactionStrategy {

  @Autowired
  public DebitTransactionStrategy(
      StatusPayableRepository statusPayableRepository,
      PayableRepository payableRepository) {
    super(statusPayableRepository, payableRepository);
  }

  public boolean check(Transaction transaction) {
    System.out.println(transaction.getPaymentMethod().getName());
    if(transaction.getPaymentMethod().getName().equals("debit")) {
      return true;
    }
    return false;
  }

  @Override
  public ResponseDTO<Transaction> processTransaction(Transaction transaction) {
    super.createPayable(transaction, LocalDate.now());

    return new ResponseDTO<Transaction>(
        "The transaction took place successfully.",
        200,
        transaction);
  }

}
