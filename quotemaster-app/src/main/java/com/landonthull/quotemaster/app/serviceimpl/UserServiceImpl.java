package com.landonthull.quotemaster.app.serviceimpl;

import com.landonthull.quotemaster.app.service.UserService;
import com.landonthull.quotemaster.app.util.JwtTokenUtil;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtTokenUtil jwtTokenUtil;

  public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
    this.userRepository = userRepository;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  public User getCurrentUser(HttpServletRequest request) {

    String header = request.getHeader("Authorization");
    String token = header.substring(7);

    String email = jwtTokenUtil.extractEmail(token);

    Optional<User> userOptional = userRepository.findByEmail(email);

    return userOptional.orElse(null);
  }
}
