package com.landonthull.quotemaster.infrastructure.postgres.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {

  boolean existsByEmail(String email);
}
