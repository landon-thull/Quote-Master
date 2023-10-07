package com.landonthull.quotemaster.app.controller;

import com.landonthull.quotemaster.app.service.AppCustomerService;
import com.landonthull.quotemaster.app.util.LoggingUtil;
import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import com.landonthull.quotemaster.core.customer.port.out.CustomerService;
import com.landonthull.quotemaster.core.customer.usecase.createcustomer.CreateCustomerRequest;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  private final CustomerService customerService;
  private final AppCustomerService appCustomerService;
  private final LoggingUtil loggingUtil;

  public CustomerController(CustomerService customerService, AppCustomerService appCustomerService,
      LoggingUtil loggingUtil) {
    this.customerService = customerService;
    this.appCustomerService = appCustomerService;
    this.loggingUtil = loggingUtil;
  }

  @PostMapping
  public Customer createCustomer(@RequestBody CreateCustomerRequest request) {
    logger.info("Create customer request initiated by user '{}'", loggingUtil.getPrincipalEmail());
    return customerService.createCustomer(request);
  }

  @GetMapping("/{id}")
  public Customer getCustomerById(@PathVariable("id") UUID id) {
    logger.info(
        "Get customer by id request initiated by user '{}'", loggingUtil.getPrincipalEmail()
    );
    return appCustomerService.getCustomerById(id);
  }
}
