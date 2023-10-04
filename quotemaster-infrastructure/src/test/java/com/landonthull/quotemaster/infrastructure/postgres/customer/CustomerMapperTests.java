package com.landonthull.quotemaster.infrastructure.postgres.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.domain.entity.CustomerStatus;
import java.sql.Timestamp;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CustomerMapperTests {

  private final CustomerMapper customerMapper = new CustomerMapper();

  @Test
  public void toJpaEntity_fieldsMatch() {
    final String name = "Planes-R-Us";
    final String notes = "They make planes";
    final String industry = "Aerospace";

    final Customer customer = new Customer(name, notes, industry);
    final JpaCustomerEntity jpaCustomer = customerMapper.toJpaEntity(customer);

    assertEquals(customer.getId(), jpaCustomer.getId());
    assertEquals(customer.getName(), jpaCustomer.getName());
    assertEquals(customer.getNotes(), jpaCustomer.getNotes());
    assertEquals(customer.getStatus(), jpaCustomer.getStatus());
    assertEquals(customer.getIndustry(), jpaCustomer.getIndustry());
    assertEquals(customer.getCreatedAt(), jpaCustomer.getCreatedAt());
    assertEquals(customer.getUpdatedAt(), jpaCustomer.getUpdatedAt());
  }

  @Test
  public void toDomainEntity_fieldsMatch() {
    final UUID id = UUID.randomUUID();
    final String name = "Planes-R-Us";
    final String notes = "They make planes";
    final CustomerStatus status = CustomerStatus.ACTIVE;
    final String industry = "Aerospace";
    final Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    final Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

    final JpaCustomerEntity jpaCustomer = new JpaCustomerEntity(
        id, name, notes, status, industry, createdAt, updatedAt
    );

    final Customer customer = customerMapper.toDomainEntity(jpaCustomer);

    assertEquals(customer.getId(), jpaCustomer.getId());
    assertEquals(customer.getName(), jpaCustomer.getName());
    assertEquals(customer.getNotes(), jpaCustomer.getNotes());
    assertEquals(customer.getStatus(), jpaCustomer.getStatus());
    assertEquals(customer.getIndustry(), jpaCustomer.getIndustry());
    assertEquals(customer.getCreatedAt(), jpaCustomer.getCreatedAt());
    assertEquals(customer.getUpdatedAt(), jpaCustomer.getUpdatedAt());
  }
}
