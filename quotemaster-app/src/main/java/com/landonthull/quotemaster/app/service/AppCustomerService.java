package com.landonthull.quotemaster.app.service;

import com.landonthull.quotemaster.core.customer.domain.entity.Customer;
import java.util.UUID;

public interface AppCustomerService {

  Customer getCustomerById(UUID id);
}
