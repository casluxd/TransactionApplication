package com.transactionapplication.transactionapplication.controllers.transaction;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.exceptions.StrategyNotFound;
import com.transactionapplication.transactionapplication.exceptions.transaction.PaymentMethodInvalid;

@RestControllerAdvice(assignableTypes = TransactionController.class)
public class TransactionControllerExceptionHandler {

  @ExceptionHandler({ DataAccessException.class, StrategyNotFound.class })
  public ResponseEntity<?> handleInternalServerError(Exception ex) {
    return ResponseEntity.internalServerError().build();
  }

  @ExceptionHandler(PaymentMethodInvalid.class)
  public ResponseEntity<?> handleTransactionInvalid(PaymentMethodInvalid ex) {
    return ResponseEntity.badRequest().body(new ResponseDTO<String>(
        "Unable to proceed with the transaction.",
        400,
        ex.getMessage()));
  }
}
