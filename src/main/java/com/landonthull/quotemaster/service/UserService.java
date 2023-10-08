package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.dto.CreateUserResponse;

public interface UserService {

  CreateUserResponse createUser(CreateUserRequest request);
}
