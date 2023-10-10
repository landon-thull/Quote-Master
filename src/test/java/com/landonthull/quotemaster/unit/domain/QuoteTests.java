package com.landonthull.quotemaster.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.domain.QuoteStatus;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class QuoteTests {

  @Test
  void getters_setters_functionAsExpected() {
    final Long ID = 1L;
    final QuoteStatus STATUS = QuoteStatus.QUOTED;
    final Customer CUSTOMER = new Customer();
    final Date REQUESTED_ON = new Date();
    final Date EFFECTIVE_ON = new Date();
    final Date EXPIRES_ON = new Date();
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    Quote quote = new Quote();

    quote.setId(ID);
    quote.setStatus(STATUS);
    quote.setCustomer(CUSTOMER);
    quote.setRequestedOn(REQUESTED_ON);
    quote.setEffectiveOn(EFFECTIVE_ON);
    quote.setExpiresOn(EXPIRES_ON);
    quote.setCreatedAt(CREATED_AT);
    quote.setUpdatedAt(UPDATED_AT);

    assertEquals(ID, quote.getId());
    assertEquals(STATUS, quote.getStatus());
    assertEquals(CUSTOMER, quote.getCustomer());
    assertEquals(REQUESTED_ON, quote.getRequestedOn());
    assertEquals(EFFECTIVE_ON, quote.getEffectiveOn());
    assertEquals(EXPIRES_ON, quote.getExpiresOn());
    assertEquals(CREATED_AT, quote.getCreatedAt());
    assertEquals(UPDATED_AT, quote.getUpdatedAt());
  }

  @Test
  void allArgsConstructor_setsValuesCorrectly() {
    final Long ID = 1L;
    final QuoteStatus STATUS = QuoteStatus.QUOTED;
    final Customer CUSTOMER = new Customer();
    final Date REQUESTED_ON = new Date();
    final Date EFFECTIVE_ON = new Date();
    final Date EXPIRES_ON = new Date();
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    Quote quote = new Quote(
        ID,
        STATUS,
        CUSTOMER,
        REQUESTED_ON,
        EFFECTIVE_ON,
        EXPIRES_ON,
        CREATED_AT,
        UPDATED_AT
    );

    assertEquals(ID, quote.getId());
    assertEquals(STATUS, quote.getStatus());
    assertEquals(CUSTOMER, quote.getCustomer());
    assertEquals(REQUESTED_ON, quote.getRequestedOn());
    assertEquals(EFFECTIVE_ON, quote.getEffectiveOn());
    assertEquals(EXPIRES_ON, quote.getExpiresOn());
    assertEquals(CREATED_AT, quote.getCreatedAt());
    assertEquals(UPDATED_AT, quote.getUpdatedAt());
  }

  @Test
  void someArgsConstructor_setsValuesCorrectly() {
    final QuoteStatus STATUS = QuoteStatus.QUOTED;
    final Customer CUSTOMER = new Customer();
    final Date REQUESTED_ON = new Date();

    Quote quote = new Quote(STATUS, CUSTOMER , REQUESTED_ON);

    assertEquals(STATUS, quote.getStatus());
    assertEquals(CUSTOMER, quote.getCustomer());
    assertEquals(REQUESTED_ON, quote.getRequestedOn());
  }
}
