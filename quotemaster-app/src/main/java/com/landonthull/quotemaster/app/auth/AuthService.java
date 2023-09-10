package com.landonthull.quotemaster.app.auth;

public interface AuthService {

  SignInResponse signIn(SignInRequest request);
}
