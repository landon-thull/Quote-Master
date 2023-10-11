package com.landonthull.quotemaster.serviceimpl;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;
import com.landonthull.quotemaster.exception.ResourceAlreadyExistsException;
import com.landonthull.quotemaster.exception.ResourceNotFoundException;
import com.landonthull.quotemaster.repository.CustomerRepository;
import com.landonthull.quotemaster.service.CustomerService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
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
}
