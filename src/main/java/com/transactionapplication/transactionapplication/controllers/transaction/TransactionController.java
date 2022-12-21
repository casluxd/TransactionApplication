package com.transactionapplication.transactionapplication.controllers.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionapplication.transactionapplication.dtos.ResponseDTO;
import com.transactionapplication.transactionapplication.dtos.TransactionDTO;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.repositories.PayableRepository;
import com.transactionapplication.transactionapplication.repositories.TransactionRepository;
import com.transactionapplication.transactionapplication.services.TransactionService;

@RestController
@RequestMapping("/")
public class TransactionController {

  private final TransactionService transactionService;
  private final TransactionRepository transactionRepository;
  private final PayableRepository payableRepository;

  @Autowired
  public TransactionController(
      TransactionService transactionService,
      TransactionRepository transactionRepository,
      PayableRepository payableRepository) {
    this.transactionService = transactionService;
    this.transactionRepository = transactionRepository;
    this.payableRepository = payableRepository;
  }

  @GetMapping("/transactions")
  public ResponseEntity<?> getAllTransactions() {
    List<Transaction> transactions = this.transactionRepository.findAll();

    return ResponseEntity.ok(
        new ResponseDTO<List<Transaction>>(
            "List of all user transactions",
            200,
            transactions));
  }

  @PostMapping("/transaction")
  public ResponseEntity<?> processTransaction(@RequestBody TransactionDTO transactionDTO) {
    return ResponseEntity.ok().body(this.transactionService.processTransaction(transactionDTO));
  }

  @GetMapping("/payables")
  public ResponseEntity<?> getAllPayables() {
    return ResponseEntity.ok(this.payableRepository.findAll());
  }

}
