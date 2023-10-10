package com.landonthull.quotemaster.serviceimpl;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.SignInRequest;
import com.landonthull.quotemaster.dto.SignInResponse;
import com.landonthull.quotemaster.repository.UserRepository;
import com.landonthull.quotemaster.service.AuthService;
import com.landonthull.quotemaster.util.JwtTokenUtil;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
    } catch (RuntimeException e) {
      throw new BadCredentialsException("Invalid email or password");
    }

    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isEmpty()) {
      throw new BadCredentialsException("Invalid email or password");
    }

    User user = userOptional.get();
    return new SignInResponse(jwtTokenUtil.generateAccessToken(user));
  }
}
