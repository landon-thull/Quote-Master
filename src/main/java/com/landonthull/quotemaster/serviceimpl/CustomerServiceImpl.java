package com.landonthull.quotemaster.serviceimpl;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.domain.QuoteStatus;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;
import com.landonthull.quotemaster.dto.CustomerQuotesInfoDto;
import com.landonthull.quotemaster.exception.ResourceAlreadyExistsException;
import com.landonthull.quotemaster.exception.ResourceNotFoundException;
import com.landonthull.quotemaster.repository.CustomerRepository;
import com.landonthull.quotemaster.repository.QuoteRepository;
import com.landonthull.quotemaster.service.CustomerService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final QuoteRepository quoteRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository, QuoteRepository quoteRepository) {
    this.customerRepository = customerRepository;
    this.quoteRepository = quoteRepository;
  }

  @Override
  public Customer createCustomer(CreateCustomerRequest request) {

    if (customerRepository.existsByName(request.getName())) {
      throw new ResourceAlreadyExistsException("Customer", "name", request.getName());
    }

    Customer customer = new Customer(request.getName(), request.getNotes(), request.getIndustry());

    return customerRepository.save(customer);
  }

  @Override
  public Customer getCustomerById(Long id) {

    Optional<Customer> customerOptional = customerRepository.findById(id);

    if (customerOptional.isEmpty()) {
      throw new ResourceNotFoundException("Customer", "ID", id.toString());
    }

    return customerOptional.get();
  }

  @Override
  public Page<Quote> getQuotesByCustomerId(Long id, int page, int count) {
    Optional<Customer> customerOptional = customerRepository.findById(id);

    if (customerOptional.isEmpty()) {
      throw new ResourceNotFoundException("Customer", "id", id.toString());
    }

    Customer customer = customerOptional.get();

    Pageable pageable = PageRequest.of(page, count);

    Page<Quote> quotes = quoteRepository.findAllByCustomer(customer, pageable);

    return quotes;
  }

  @Override
  public Page<Customer> getAllCustomers(int page, int count) {
    Pageable pageable = PageRequest.of(page, count);

    return customerRepository.findAll(pageable);
  }

  @Override
  public CustomerQuotesInfoDto getCustomerQuotesInfo(Customer customer) {
    return new CustomerQuotesInfoDto(
        quoteRepository.countByCustomer(customer),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.OPEN),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.QUOTED),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.NO_QUOTE),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.CANCELLED)
    );
  }
}
