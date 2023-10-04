package com.landonthull.quotemaster.infrastructure.postgres.customer;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.port.in.CustomerRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresCustomerRepository implements CustomerRepository {

  private final JpaCustomerRepository jpaCustomerRepository;
  private final CustomerMapper customerMapper;

  public PostgresCustomerRepository(JpaCustomerRepository jpaCustomerRepository,
      CustomerMapper customerMapper) {
    this.jpaCustomerRepository = jpaCustomerRepository;
    this.customerMapper = customerMapper;
  }

  public Customer save(Customer customer) {
    JpaCustomerEntity jpaCustomer = customerMapper.toJpaEntity(customer);
    JpaCustomerEntity savedJpaCustomer = jpaCustomerRepository.save(jpaCustomer);
    return customerMapper.toDomainEntity(savedJpaCustomer);
  }

  public Optional<Customer> findById(UUID id) {
    Optional<JpaCustomerEntity> existingJpaCustomerOptional = jpaCustomerRepository.findById(id);
    if (existingJpaCustomerOptional.isEmpty()) {
      return Optional.empty();
    }

    JpaCustomerEntity existingJpaCustomer = existingJpaCustomerOptional.get();
    return Optional.of(customerMapper.toDomainEntity(existingJpaCustomer));
  }

  public boolean existsByName(String name) {
    return jpaCustomerRepository.existsByName(name);
  }
}
