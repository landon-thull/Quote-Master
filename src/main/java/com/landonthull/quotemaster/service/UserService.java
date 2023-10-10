package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

  User createUser(CreateUserRequest request);

  User getCurrentUser(HttpServletRequest request);
}
