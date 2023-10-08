package com.landonthull.quotemaster.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.landonthull.quotemaster.domain.Customer;
import java.sql.Timestamp;
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

    Customer customer = new Customer();
    customer.setId(ID);
    customer.setName(NAME);
    customer.setNotes(NOTES);
    customer.setIndustry(INDUSTRY);
    customer.setCreatedAt(CREATED_AT);
    customer.setUpdatedAt(UPDATED_AT);

    assertEquals(customer.getId(), ID);
    assertEquals(customer.getName(), NAME);
    assertEquals(customer.getNotes(), NOTES);
    assertEquals(customer.getIndustry(), INDUSTRY);
    assertEquals(customer.getCreatedAt(), CREATED_AT);
    assertEquals(customer.getUpdatedAt(), UPDATED_AT);
  }

  @Test
  void allArgsConstructor_setsValuesCorrectly() {
    final Long ID = 1L;
    final String NAME = "name";
    final String NOTES = "notes here";
    final String INDUSTRY = "software";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    Customer customer = new Customer(
        ID,
        NAME,
        NOTES,
        INDUSTRY,
        CREATED_AT,
        UPDATED_AT
    );

    assertEquals(customer.getId(), ID);
    assertEquals(customer.getName(), NAME);
    assertEquals(customer.getNotes(), NOTES);
    assertEquals(customer.getIndustry(), INDUSTRY);
    assertEquals(customer.getCreatedAt(), CREATED_AT);
    assertEquals(customer.getUpdatedAt(), UPDATED_AT);
  }
}
