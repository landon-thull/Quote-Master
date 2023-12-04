package com.landonthull.quotemaster.controller;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.QuoteStatus;
import com.landonthull.quotemaster.dto.*;
import com.landonthull.quotemaster.repository.QuoteRepository;
import com.landonthull.quotemaster.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final QuoteRepository quoteRepository;

  public CustomerController(CustomerService customerService, QuoteRepository quoteRepository) {
    this.customerService = customerService;
    this.quoteRepository = quoteRepository;
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority({'ADMINISTRATOR', 'SALES_MANAGER', 'SALES_REPRESENTATIVE'})")
  @Operation(summary = "Create customer", description = "Create a new customer")
  public ResponseEntity<CreateCustomerResponse> createCustomer(
      @Valid @RequestBody CreateCustomerRequest request
  ) throws URISyntaxException {

    Customer savedCustomer = customerService.createCustomer(request);
    CreateCustomerResponse response = new CreateCustomerResponse(
        savedCustomer.getId(),
        savedCustomer.getCreatedAt()
    );

    return ResponseEntity
        .created(new URI("/customers/" + savedCustomer.getId()))
        .body(response);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get customer by ID", description = "Get existing customer by ID (number)")
  public CustomerDto getCustomerById(@PathVariable Long id) {
    Customer customer = customerService.getCustomerById(id);

    CustomerQuotesInfoDto quotesInfo = new CustomerQuotesInfoDto(
        quoteRepository.countByCustomer(customer),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.OPEN),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.QUOTED),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.NO_QUOTE),
        quoteRepository.countByCustomerAndStatus(customer, QuoteStatus.CANCELLED)
    );

    return new CustomerDto(customer, quotesInfo);
  }

//  @GetMapping("/{id}/quotes")
//  @Operation()
//  public PaginationResponse<QuoteDto> getQuotesByCustomerId(
//    @PathVariable Long id, @RequestParam int page, @RequestParam int limit
//  ) {
//
//  }

  @GetMapping
  @Operation(summary = "Get all Customers",
      description = "Get all customers with pagination. Optional sorting and filtering.")
  public PaginationResponse<CustomerDto> getAllCustomers(
      @RequestParam int page, @RequestParam int limit
  ) {
    Page<Customer> customers = customerService.getAllCustomers(page, limit);

    List<CustomerDto> customerDtos = customers
        .get()
        .map(
            customer -> new CustomerDto(customer, customerService.getCustomerQuotesInfo(customer))
        )
        .toList();

    PaginationInfo pagination = new PaginationInfo(
        customers.getTotalElements(),
        page,
        customers.getTotalPages(),
        page + 1 >= customers.getTotalPages() ? null : page + 1,
        page == 0 ? null : page - 1
    );

    return new PaginationResponse<>(customerDtos, pagination);
  }
}
