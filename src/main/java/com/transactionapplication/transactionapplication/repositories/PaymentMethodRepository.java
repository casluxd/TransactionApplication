package com.transactionapplication.transactionapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionapplication.transactionapplication.entities.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
  
}
