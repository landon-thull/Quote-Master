package com.landonthull.quotemaster.app.auth;

import com.landonthull.quotemaster.app.util.JwtTokenUtil;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserRepository userRepository;

  public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
      UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userRepository = userRepository;
  }

  @Override
  public SignInResponse signIn(SignInRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isEmpty()) {
      throw new IllegalArgumentException("Invalid email or password");
    }

    User user = userOptional.get();
    return new SignInResponse(jwtTokenUtil.generateAccessToken(user));
  }
}
