package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.CreateUserRequest;

public interface UserService {

  User createUser(CreateUserRequest request);
}
