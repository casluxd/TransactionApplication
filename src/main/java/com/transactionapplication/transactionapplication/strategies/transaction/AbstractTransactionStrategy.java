package com.transactionapplication.transactionapplication.strategies.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.transactionapplication.transactionapplication.entities.Payable;
import com.transactionapplication.transactionapplication.entities.StatusPayable;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.enums.StatusPayableEnum;
import com.transactionapplication.transactionapplication.repositories.PayableRepository;
import com.transactionapplication.transactionapplication.repositories.StatusPayableRepository;

public abstract class AbstractTransactionStrategy implements TransactionStrategy{
  
  protected final StatusPayableRepository statusPayableRepository;
  protected final PayableRepository payableRepository;

  public AbstractTransactionStrategy(
      StatusPayableRepository statusPayableRepository,
      PayableRepository payableRepository) {
    this.statusPayableRepository = statusPayableRepository;
    this.payableRepository = payableRepository;
  }

  public void createPayable(Transaction transaction, LocalDate paymentDate) {
    // calculate value to receive
    BigDecimal processingFee = new BigDecimal(transaction.getPaymentMethod().getProcessingFee() / 100);
    BigDecimal valueToReceive = transaction.getAmount().subtract(transaction.getAmount().multiply(processingFee));

    StatusPayable statusPayable = this.statusPayableRepository
        .findById((long) StatusPayableEnum.WAITING_FUNDS.getValue()).get();

    this.payableRepository.save(
        new Payable(
            statusPayable,
            paymentDate,
            valueToReceive));
  }
}
