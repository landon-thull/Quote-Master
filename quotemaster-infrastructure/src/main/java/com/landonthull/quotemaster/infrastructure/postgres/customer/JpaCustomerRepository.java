package com.landonthull.quotemaster.infrastructure.postgres.customer;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, UUID> {

    boolean existsByName(String name);
}
