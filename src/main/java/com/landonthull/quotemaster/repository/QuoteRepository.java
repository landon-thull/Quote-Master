package com.landonthull.quotemaster.repository;

import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.domain.Quote;
import com.landonthull.quotemaster.domain.QuoteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  long countByCustomer(Customer customer);

  Page<Quote> findAllByCustomer(Customer customer, Pageable pageable);

  long countByCustomerAndStatus(Customer customer, QuoteStatus status);
}
