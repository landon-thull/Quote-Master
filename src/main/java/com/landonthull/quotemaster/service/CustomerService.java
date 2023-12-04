package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;
import com.landonthull.quotemaster.dto.CustomerQuotesInfoDto;
import org.springframework.data.domain.Page;

public interface CustomerService {

  Customer createCustomer(CreateCustomerRequest request);

  Customer getCustomerById(Long id);

  Page<Quote> getQuotesByCustomerId(Long customerId, int page, int count);

  Page<Customer> getAllCustomers(int page, int count);

  CustomerQuotesInfoDto getCustomerQuotesInfo(Customer customer);
}
