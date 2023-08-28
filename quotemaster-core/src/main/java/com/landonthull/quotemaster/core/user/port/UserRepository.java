package com.landonthull.quotemaster.core.user.port;

import com.landonthull.quotemaster.core.user.domain.entity.User;

public interface UserRepository {

  User save(User user);

  boolean existsByEmail(String email);
}
