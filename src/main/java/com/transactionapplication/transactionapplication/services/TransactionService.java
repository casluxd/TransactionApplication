package com.transactionapplication.transactionapplication.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.dtos.TransactionDTO;
import com.transactionapplication.transactionapplication.entities.PaymentMethod;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.exceptions.StrategyNotFound;
import com.transactionapplication.transactionapplication.exceptions.transaction.PaymentMethodInvalid;
import com.transactionapplication.transactionapplication.repositories.PaymentMethodRepository;
import com.transactionapplication.transactionapplication.repositories.TransactionRepository;
import com.transactionapplication.transactionapplication.strategies.transaction.TransactionStrategy;

@Service
public class TransactionService {
  private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

  private final TransactionRepository transactionRepository;
  private final PaymentMethodRepository paymentMethodRepository;
  private final List<TransactionStrategy> transactionStrategies;

  @Autowired
  public TransactionService(
      TransactionRepository transactionRepository,
      PaymentMethodRepository paymentMethodRepository,
      List<TransactionStrategy> transactionsStrategies) {
    this.paymentMethodRepository = paymentMethodRepository;
    this.transactionRepository = transactionRepository;
    this.transactionStrategies = transactionsStrategies;
  }

  public ResponseDTO<Transaction> processTransaction(TransactionDTO transactionDTO) {
    Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(transactionDTO.getPaymentMethodId());

    if (!paymentMethod.isPresent() || paymentMethod.get() == null) {
      throw new PaymentMethodInvalid();
    }

    Transaction transaction;

    try {
      transaction = transactionRepository.save(new Transaction(
          transactionDTO.getAmount(),
          transactionDTO.getDescription(),
          paymentMethod.get(),
          transactionDTO.getLastFourDigitsCardNumber(),
          transactionDTO.getCardOwnerName(),
          transactionDTO.getCardValidateDate(),
          transactionDTO.getCardVerificationCode()));
    } catch (DataAccessException e) {
      logger.error("An error occurred while saving data to the database: " + e.getMessage());
      throw e;
    }

    for (TransactionStrategy strategy : transactionStrategies) {
      if (strategy.check(transaction)) {
        return strategy.processTransaction(transaction);
      } else {
        throw new StrategyNotFound();
      }
    }

    return null;
  }
}
