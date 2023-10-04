package com.landonthull.quotemaster.core.customer.port.out;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.port.in.CustomerRepository;
import com.landonthull.quotemaster.core.customer.usecase.createcustomer.CreateCustomer;
import com.landonthull.quotemaster.core.customer.usecase.createcustomer.CreateCustomerRequest;

public class CustomerService {

  private final CreateCustomer createCustomer;

  public CustomerService(CustomerRepository customerRepository) {
    this.createCustomer = new CreateCustomer(customerRepository);
  }

  public Customer createCustomer(CreateCustomerRequest request) {
    return createCustomer.execute(request);
  }
}
