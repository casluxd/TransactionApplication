package com.transactionapplication.transactionapplication.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.transactionapplication.transactionapplication.dtos.PayablesDTO;
import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.dtos.TransactionDTO;
import com.transactionapplication.transactionapplication.entities.Payable;
import com.transactionapplication.transactionapplication.entities.PaymentMethod;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.exceptions.StrategyNotFound;
import com.transactionapplication.transactionapplication.exceptions.transaction.PaymentMethodInvalid;
import com.transactionapplication.transactionapplication.repositories.PayableRepository;
import com.transactionapplication.transactionapplication.repositories.PaymentMethodRepository;
import com.transactionapplication.transactionapplication.repositories.TransactionRepository;
import com.transactionapplication.transactionapplication.strategies.transaction.TransactionStrategy;

@Service
public class TransactionService {
  private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

  private final TransactionRepository transactionRepository;
  private final PaymentMethodRepository paymentMethodRepository;
  private final PayableRepository payableRepository;
  private final List<TransactionStrategy> transactionStrategies;

  @Autowired
  public TransactionService(
      TransactionRepository transactionRepository,
      PaymentMethodRepository paymentMethodRepository,
      List<TransactionStrategy> transactionsStrategies,
      PayableRepository payableRepository) {
    this.paymentMethodRepository = paymentMethodRepository;
    this.transactionRepository = transactionRepository;
    this.transactionStrategies = transactionsStrategies;
    this.payableRepository = payableRepository;
  }

  public ResponseDTO<Transaction> processTransaction(TransactionDTO transactionDTO) {
    Long paymentMethodId = transactionDTO.getPaymentMethodId();
    Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(paymentMethodId);

    if (!paymentMethod.isPresent()) {
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
      if (strategy.check(transaction.getPaymentMethod().getName())) {
        return strategy.processTransaction(transaction);
      }
    }

    throw new StrategyNotFound();
  }

  public ResponseDTO<PayablesDTO> getAllPayables() {
    List<Payable> waitingFunds = this.payableRepository.findByStatusPayableName("waiting_funds");
    List<Payable> available = this.payableRepository.findByStatusPayableName("paid");

    PayablesDTO payablesDTO = new PayablesDTO(available, waitingFunds);

    return new ResponseDTO<>(
        "Available balance and balance awaiting funds",
        200,
        payablesDTO);
  }
}
