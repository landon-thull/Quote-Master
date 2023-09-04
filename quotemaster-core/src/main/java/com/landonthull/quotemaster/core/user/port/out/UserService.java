package com.landonthull.quotemaster.core.user.port.out;

import com.landonthull.quotemaster.core.user.port.in.PasswordEncoder;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUser;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserResponse;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUser;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserRequest;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserResponse;

public class UserService {

  private final CreateUser createUser;
  private final DisableUser disableUser;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.createUser = new CreateUser(userRepository, passwordEncoder);
    this.disableUser = new DisableUser(userRepository);
  }

  public CreateUserResponse createUser(CreateUserRequest request) {
    return createUser.execute(request);
  }
  
  public DisableUserResponse disableUser(DisableUserRequest request) {
    return disableUser.execute(request);
  }
}
