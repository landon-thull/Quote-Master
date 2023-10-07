package com.landonthull.quotemaster.app.serviceimpl;

import com.landonthull.quotemaster.app.service.AppCustomerService;
import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.domain.exception.CustomerNotFoundException;
import com.landonthull.quotemaster.core.customer.port.in.CustomerRepository;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppCustomerServiceImpl implements AppCustomerService {
  private final Logger logger = LoggerFactory.getLogger(AppCustomerServiceImpl.class);

  private final CustomerRepository customerRepository;

  public AppCustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer getCustomerById(UUID id) {
    logger.info("Querying persistence layer for customer with id '{}'", id);
    Optional<Customer> customerOptional = customerRepository.findById(id);

    if (customerOptional.isEmpty()) {
      logger.info("Customer not found with id '{}'", id);
      throw new CustomerNotFoundException("id", id.toString());
    }

    logger.info("Successfully retrieved customer with id '{}' from persistence layer", id);
    return customerOptional.get();
  }
}
