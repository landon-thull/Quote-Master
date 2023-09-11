package com.landonthull.quotemaster.app.auth;

import com.landonthull.quotemaster.app.dto.SignInRequest;
import com.landonthull.quotemaster.app.dto.SignInResponse;

public interface AuthService {

  SignInResponse signIn(SignInRequest request);
}
