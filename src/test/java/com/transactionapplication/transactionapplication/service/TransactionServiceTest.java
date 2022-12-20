package com.transactionapplication.transactionapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.transactionapplication.transactionapplication.entities.PaymentMethod;
import com.transactionapplication.transactionapplication.entities.Transaction;
import com.transactionapplication.transactionapplication.repositories.PaymentMethodRepository;
import com.transactionapplication.transactionapplication.repositories.TransactionRepository;

@SpringBootTest
public class TransactionServiceTest {

  @MockBean
  private TransactionRepository transactionRepository;

  @MockBean
  private PaymentMethodRepository paymentMethodRepository;

  @Test
  public void testGetListOfAllTransactions() {
    when(transactionRepository.findAll()).thenReturn(new ArrayList<>());

    List<Transaction> transactions = transactionRepository.findAll();

    assertEquals(0, transactions.size());
  }

  @Test
  public void testCreateTransaction() {
    PaymentMethod paymentMethod = new PaymentMethod("debit");
    when(this.paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

    PaymentMethod paymentMethodSaved = paymentMethodRepository.save(paymentMethod);

    Transaction transaction = new Transaction(
        new BigDecimal("200.0"),
        "description", paymentMethodSaved, "3214", "Lucas", LocalDate.now(), "002");

    when(this.transactionRepository.save(transaction)).thenReturn(transaction);

    Transaction transactionCreated = transactionRepository.save(transaction);

    assertEquals(transaction.getDescription(), transactionCreated.getDescription());
    assertEquals(transaction.getAmount(), transactionCreated.getAmount());
    assertEquals(transaction.getCardOwnerName(), transactionCreated.getCardOwnerName());
    assertEquals(transaction.getCardValidateDate(), transactionCreated.getCardValidateDate());
    assertEquals(transaction.getCardVerificationCode(), transactionCreated.getCardVerificationCode());
    assertEquals(transaction.getLastFourDigitsCardNumber(), "XXXX-XXXX-XXXX-3214");
    assertEquals(transaction.getPaymentMethod().getName(), transactionCreated.getPaymentMethod().getName());
  }
}
