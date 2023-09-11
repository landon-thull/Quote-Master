package com.landonthull.quotemaster.infrastructure.postgres.user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {

  Optional<JpaUserEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
