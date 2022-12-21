package com.transactionapplication.transactionapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionapplication.transactionapplication.entities.Payable;

public interface PayableRepository extends JpaRepository<Payable, Long> {
  
  List<Payable> findByStatusPayableName(String name);
}
