package com.landonthull.quotemaster.core.user.usecase.disableuser;

import com.landonthull.quotemaster.core.common.domain.exception.PersistenceException;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.exception.UserNotFoundException;
import com.landonthull.quotemaster.core.user.port.UserRepository;
import com.landonthull.quotemaster.core.user.usecase.validator.UserValidator;
import java.util.Optional;

public final class DisableUser {

  private final UserRepository userRepository;

  public DisableUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public DisableUserResponse execute(DisableUserRequest request) {
    UserValidator.validateDisableUserRequest(request);

    Optional<User> existingUserOptional = userRepository.findById(request.getUserId());
    if (existingUserOptional.isEmpty()) {
      throw new UserNotFoundException(request.getUserId().toString());
    }

    User existingUser = existingUserOptional.get();

    existingUser.setActive(false);

    try {
      userRepository.save(existingUser);
    } catch (Exception e) {
      throw new PersistenceException("Error encountered while saving user data", e);
    }

    return new DisableUserResponse("User successfully disabled");
  }
}
