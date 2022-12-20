package com.transactionapplication.transactionapplication.controllers.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionapplication.transactionapplication.dtos.TransactionDTO;
import com.transactionapplication.transactionapplication.repositories.TransactionRepository;
import com.transactionapplication.transactionapplication.services.TransactionService;

@RestController
@RequestMapping("/")
public class TransactionController {
  
  private final TransactionService transactionService;
  private final TransactionRepository transactionRepository;

  @Autowired
  public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
    this.transactionService = transactionService;
    this.transactionRepository = transactionRepository;
  }


  @PostMapping("/transaction")
  public ResponseEntity<?> processTransaction(@RequestBody TransactionDTO transactionDTO) {
    return ResponseEntity.ok().body(this.transactionService.processTransaction(transactionDTO));
  } 
}
