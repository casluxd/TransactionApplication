package com.transactionapplication.transactionapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionapplication.transactionapplication.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  
}
