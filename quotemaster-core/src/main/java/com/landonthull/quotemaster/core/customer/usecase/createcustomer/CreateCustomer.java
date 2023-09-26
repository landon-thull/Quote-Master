package com.landonthull.quotemaster.core.customer.usecase.createcustomer;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.domain.exception.CustomerAlreadyExistsException;
import com.landonthull.quotemaster.core.customer.port.in.CustomerRepository;
import com.landonthull.quotemaster.core.customer.usecase.validator.CustomerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCustomer {
  private final Logger logger = LoggerFactory.getLogger(CreateCustomer.class);

  private final CustomerRepository customerRepository;
  private final CustomerValidator customerValidator = new CustomerValidator();

  public CreateCustomer(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer execute(CreateCustomerRequest request) {
    logger.info(
        String.format("Validating create customer request for customer %s.", request.getName())
    );
    customerValidator.validateCreateCustomerRequest(request);

    if (customerRepository.existsByName(request.getName())) {
      logger.info(String.format(
              "Error creating customer, customer with name %s already exists.",
              request.getName()
      ));
      throw new CustomerAlreadyExistsException(request.getName());
    }

    Customer customer = new Customer(request.getName(), request.getNotes(), request.getIndustry());

    logger.info(String.format("Saving customer with name %s.", customer.getName()));
    return customerRepository.save(customer);
  }
}
