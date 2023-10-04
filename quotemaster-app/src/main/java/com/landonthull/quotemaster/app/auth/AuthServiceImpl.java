package com.landonthull.quotemaster.app.auth;

import com.landonthull.quotemaster.app.dto.SignInRequest;
import com.landonthull.quotemaster.app.dto.SignInResponse;
import com.landonthull.quotemaster.app.util.JwtTokenUtil;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

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
    logger.info(String.format("Processing login request for user %s.", request.getEmail()));
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
      logger.info(String.format("User %s successfully authenticated.", request.getEmail()));
    } catch (RuntimeException e) {
      logger.info(String.format(
          "Login request failed for user %s due to invalid email or password.",
          request.getEmail()
      ));
      throw new BadCredentialsException("Invalid email or password");
    }

    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isEmpty()) {
      logger.info(String.format(
          "Error encountered while retrieving user %s from persistence layer.",
          request.getEmail()
      ));
      throw new IllegalArgumentException();
    }

    User user = userOptional.get();
    logger.info(String.format(
        "Successfully retrieved user %s from persistence layer.",
        request.getEmail()
    ));
    return new SignInResponse(jwtTokenUtil.generateAccessToken(user));
  }
}
