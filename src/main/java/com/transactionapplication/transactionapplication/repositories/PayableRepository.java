package com.transactionapplication.transactionapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionapplication.transactionapplication.entities.Payable;

public interface PayableRepository extends JpaRepository<Payable, Long> {
  
}
