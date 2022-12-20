package com.transactionapplication.transactionapplication.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PaymentMethodTest {

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreatePaymentMethod() {
    PaymentMethod paymentMethodSaved = this.entityManager.persistAndFlush(new PaymentMethod("debit"));

    PaymentMethod paymentMethodOptional = this.entityManager.find(PaymentMethod.class, paymentMethodSaved.getId());

    assertNotNull(paymentMethodOptional);
    assertEquals(paymentMethodSaved.getName(), paymentMethodOptional.getName());
  }
}
