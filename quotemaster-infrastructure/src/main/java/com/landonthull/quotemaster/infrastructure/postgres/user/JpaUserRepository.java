package com.landonthull.quotemaster.infrastructure.postgres.user;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
