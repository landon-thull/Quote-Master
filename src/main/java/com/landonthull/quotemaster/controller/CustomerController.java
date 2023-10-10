package com.landonthull.quotemaster.controller;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;
import com.landonthull.quotemaster.dto.CreateCustomerResponse;
import com.landonthull.quotemaster.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
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
}
