package com.landonthull.quotemaster.core.user.usecase.createuser;

import com.landonthull.quotemaster.core.common.domain.exception.PersistenceException;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.exception.UserAlreadyExistsException;
import com.landonthull.quotemaster.core.user.port.in.PasswordEncoder;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import com.landonthull.quotemaster.core.user.usecase.validator.UserValidator;
import java.sql.Timestamp;
import java.util.UUID;

public final class CreateUser {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public CreateUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public CreateUserResponse execute(final CreateUserRequest request) {
    UserValidator.validateCreateUserRequest(request);

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException(request.getEmail());
    }

    User user = new User();
    user.setId(UUID.randomUUID());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setActive(true);
    user.setRole(request.getUserRole());
    user.setLastName(request.getLastName());
    user.setFirstName(request.getFirstName());
    user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

    try {
      userRepository.save(user);
    } catch (Exception e) {
      throw new PersistenceException("Error encountered while saving user data", e);
    }

    return new CreateUserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
  }
}
