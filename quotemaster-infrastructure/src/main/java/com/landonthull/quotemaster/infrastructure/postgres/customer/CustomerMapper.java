package com.landonthull.quotemaster.infrastructure.postgres.customer;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public JpaCustomerEntity toJpaEntity(Customer customer) {
    JpaCustomerEntity jpaCustomerEntity = new JpaCustomerEntity();

    jpaCustomerEntity.setId(customer.getId());
    jpaCustomerEntity.setName(customer.getName());
    jpaCustomerEntity.setNotes(customer.getNotes());
    jpaCustomerEntity.setStatus(customer.getStatus());
    jpaCustomerEntity.setIndustry(customer.getIndustry());
    jpaCustomerEntity.setCreatedAt(customer.getCreatedAt());
    jpaCustomerEntity.setUpdatedAt(customer.getUpdatedAt());

    return jpaCustomerEntity;
  }

  public Customer toDomainEntity(JpaCustomerEntity jpaCustomerEntity) {
    Customer customer = new Customer();

    customer.setId(jpaCustomerEntity.getId());
    customer.setName(jpaCustomerEntity.getName());
    customer.setNotes(jpaCustomerEntity.getNotes());
    customer.setStatus(jpaCustomerEntity.getStatus());
    customer.setIndustry(jpaCustomerEntity.getIndustry());
    customer.setCreatedAt(jpaCustomerEntity.getCreatedAt());
    customer.setUpdatedAt(jpaCustomerEntity.getUpdatedAt());

    return customer;
  }
}
