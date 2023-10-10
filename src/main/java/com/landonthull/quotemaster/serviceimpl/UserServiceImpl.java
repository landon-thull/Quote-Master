package com.landonthull.quotemaster.serviceimpl;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.exception.ResourceAlreadyExistsException;
import com.landonthull.quotemaster.repository.UserRepository;
import com.landonthull.quotemaster.service.UserService;
import com.landonthull.quotemaster.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtTokenUtil jwtTokenUtil;

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      JwtTokenUtil jwtTokenUtil) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  public User createUser(CreateUserRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ResourceAlreadyExistsException("User", "email", request.getEmail());
    }

    final String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());

    User user = new User(
        request.getEmail(),
        hashedPassword,
        request.getLastName(),
        request.getFirstName()
    );

    return userRepository.save(user);
  }

  @Override
  public User getCurrentUser(HttpServletRequest request) {

    String header = request.getHeader("Authorization");

    // slice 'Bearer ' off of token
    String token = header.substring(7);

    String email = jwtTokenUtil.extractEmail(token);

    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isEmpty()) {
      throw new IllegalArgumentException();
    }

    return userOptional.get();
  }
}
