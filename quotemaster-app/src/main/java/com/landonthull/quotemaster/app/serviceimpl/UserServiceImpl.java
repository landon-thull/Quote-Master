package com.landonthull.quotemaster.app.serviceimpl;

import com.landonthull.quotemaster.app.service.UserService;
import com.landonthull.quotemaster.app.util.JwtTokenUtil;
import com.landonthull.quotemaster.app.util.LoggingUtil;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private final LoggingUtil loggingUtil = new LoggingUtil();

  private final UserRepository userRepository;
  private final JwtTokenUtil jwtTokenUtil;

  public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
    this.userRepository = userRepository;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  public User getCurrentUser(HttpServletRequest request) {

    String header = request.getHeader("Authorization");
    String token = header.substring(7);

    logger.info("Extracting JWT from get current user request from user {}",
        loggingUtil.getPrincipalEmail());

    String email = jwtTokenUtil.extractEmail(token);

    Optional<User> userOptional = userRepository.findByEmail(email);

    logger.info("Retrieved user information for user {} from persistence layer",
        loggingUtil.getPrincipalEmail());

    return userOptional.orElse(null);
  }
}
