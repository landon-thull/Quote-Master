package com.landonthull.quotemaster.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CustomerTests {

  @Test
  void getters_setters_functionAsExpected() {
    final Long ID = 1L;
    final String NAME = "name";
    final String NOTES = "notes here";
    final String INDUSTRY = "software";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());
    final List<Quote> QUOTES = new ArrayList<>();

    Customer customer = new Customer();
    customer.setId(ID);
    customer.setName(NAME);
    customer.setNotes(NOTES);
    customer.setIndustry(INDUSTRY);
    customer.setCreatedAt(CREATED_AT);
    customer.setUpdatedAt(UPDATED_AT);
    customer.setQuotes(QUOTES);

    assertEquals(customer.getId(), ID);
    assertEquals(customer.getName(), NAME);
    assertEquals(customer.getNotes(), NOTES);
    assertEquals(customer.getIndustry(), INDUSTRY);
    assertEquals(customer.getCreatedAt(), CREATED_AT);
    assertEquals(customer.getUpdatedAt(), UPDATED_AT);
    assertEquals(customer.getQuotes(), QUOTES);
  }

  @Test
  void allArgsConstructor_setsValuesCorrectly() {
    final Long ID = 1L;
    final String NAME = "name";
    final String NOTES = "notes here";
    final String INDUSTRY = "software";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());
    final List<Quote> QUOTES = new ArrayList<>();

    Customer customer = new Customer(
        ID,
        NAME,
        NOTES,
        INDUSTRY,
        CREATED_AT,
        UPDATED_AT,
        QUOTES
    );

    assertEquals(customer.getId(), ID);
    assertEquals(customer.getName(), NAME);
    assertEquals(customer.getNotes(), NOTES);
    assertEquals(customer.getIndustry(), INDUSTRY);
    assertEquals(customer.getCreatedAt(), CREATED_AT);
    assertEquals(customer.getUpdatedAt(), UPDATED_AT);
    assertEquals(customer.getQuotes(), QUOTES);
  }
}
