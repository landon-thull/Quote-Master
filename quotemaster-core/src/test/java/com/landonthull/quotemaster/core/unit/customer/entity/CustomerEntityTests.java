package com.landonthull.quotemaster.core.unit.customer.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.domain.entity.CustomerStatus;
import java.sql.Timestamp;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CustomerEntityTests {

  @Test
  public void customerEntity_allArgsConstructor() {
    Customer customer = new Customer(
        "Airplane Company",
        "Makes airplanes",
        "Aerospace"
    );

    assertEquals(customer.getName(), "Airplane Company");
    assertEquals(customer.getNotes(), "Makes airplanes");
    assertEquals(customer.getIndustry(), "Aerospace");

    assertNotNull(customer.getId());
    assertNotNull(customer.getCreatedAt());
    assertNotNull(customer.getUpdatedAt());

    // default customer status of active
    assertEquals(customer.getStatus(), CustomerStatus.ACTIVE);
  }

  @Test
  public void customerEntity_noArgsConstructor_populatesFields() {
    Customer customer = new Customer();

    assertNotNull(customer.getId());
    assertNotNull(customer.getCreatedAt());
    assertNotNull(customer.getUpdatedAt());
  }

  @Test
  public void customerEntity_hasUniqueId() {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    assertNotEquals(customer1.getId(), customer2.getId());
  }

  @Test
  public void customerEntity_getters_setters() {
    Customer customer = new Customer();

    final UUID id = UUID.randomUUID();
    final String name = "Airplane Company";
    final String notes = "They make airplanes";
    final CustomerStatus status = CustomerStatus.ARCHIVE;
    final String industry = "Aerospace";
    final Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    final Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

    customer.setId(id);
    assertEquals(customer.getId(), id);

    customer.setName(name);
    assertEquals(customer.getName(), name);

    customer.setNotes(notes);
    assertEquals(customer.getNotes(), notes);

    customer.setStatus(status);
    assertEquals(customer.getStatus(), status);

    customer.setIndustry(industry);
    assertEquals(customer.getIndustry(), industry);

    customer.setCreatedAt(createdAt);
    assertEquals(customer.getCreatedAt(), createdAt);

    customer.setUpdatedAt(updatedAt);
    assertEquals(customer.getUpdatedAt(), updatedAt);
  }
}
