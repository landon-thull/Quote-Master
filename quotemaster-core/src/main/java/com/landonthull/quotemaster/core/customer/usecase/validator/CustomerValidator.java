package com.landonthull.quotemaster.core.customer.usecase.validator;

import com.landonthull.quotemaster.core.common.domain.exception.RequestValidationException;
import com.landonthull.quotemaster.core.customer.usecase.createcustomer.CreateCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerValidator {
  private final Logger logger = LoggerFactory.getLogger(CustomerValidator.class);

  public void validateCreateCustomerRequest(CreateCustomerRequest request) {
    if (request == null) {
      logger.info("Invalid create customer request, request should not be null.");
      throw new RequestValidationException("Create customer request should not be null");
    }
    if (request.getName() == null || request.getName().isBlank()) {
      logger.info("Invalid create customer request, customer name should not be null.");
      throw new RequestValidationException("Customer name is required");
    }
    if (request.getNotes() == null || request.getNotes().isBlank()) {
      logger.info("Invalid create customer request, customer notes should not be null.");
      throw new RequestValidationException("Customer notes are required");
    }
    if (request.getIndustry() == null || request.getIndustry().isBlank()) {
      logger.info("Invalid create customer request, customer industry should not be null.");
      throw new RequestValidationException("Customer industry is required");
    }

    logger.info(
        String.format("Validated create customer request for customer %s.", request.getName())
    );
  }
}
