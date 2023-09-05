package com.landonthull.quotemaster.core.user.port.in;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  User save(User user);

  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
