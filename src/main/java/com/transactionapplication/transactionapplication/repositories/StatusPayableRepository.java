package com.transactionapplication.transactionapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionapplication.transactionapplication.entities.StatusPayable;

public interface StatusPayableRepository extends JpaRepository<StatusPayable, Long> {
  
}
