package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;

public interface CustomerService {

  Customer createCustomer(CreateCustomerRequest request);
}
