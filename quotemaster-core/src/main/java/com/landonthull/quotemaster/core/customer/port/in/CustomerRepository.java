package com.landonthull.quotemaster.core.customer.port.in;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

  Customer save(Customer customer);

  Optional<Customer> findById(UUID uuid);

  boolean existsByName(String name);
}
