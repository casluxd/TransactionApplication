package com.transactionapplication.transactionapplication.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class StatusPayableTest {
  
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateStatusPayable() {
    StatusPayable statusPayableSaved = this.entityManager.persistAndFlush(new StatusPayable(
      "paid"
    ));

    StatusPayable statusPayableOptional = this.entityManager.find(StatusPayable.class, statusPayableSaved.getId());

    assertNotNull(statusPayableOptional);
    assertEquals(statusPayableSaved.getName(), statusPayableOptional.getName());
  }

}
