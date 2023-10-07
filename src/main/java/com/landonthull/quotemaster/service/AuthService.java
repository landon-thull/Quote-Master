package com.landonthull.quotemaster.service;

import com.landonthull.quotemaster.dto.SignInRequest;
import com.landonthull.quotemaster.dto.SignInResponse;

public interface AuthService {

  SignInResponse signIn(SignInRequest request);
}
