package com.landonthull.quotemaster.app.service;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

  User getCurrentUser(HttpServletRequest request);
}
