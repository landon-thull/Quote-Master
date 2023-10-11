package com.landonthull.quotemaster.repository;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.domain.QuoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  long countByCustomer(Customer customer);

  long countByCustomerAndStatus(Customer customer, QuoteStatus status);
}
