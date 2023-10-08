package com.landonthull.quotemaster.serviceimpl;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.dto.CreateUserResponse;
import com.landonthull.quotemaster.exception.ResourceAlreadyExistsException;
import com.landonthull.quotemaster.repository.UserRepository;
import com.landonthull.quotemaster.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public CreateUserResponse createUser(CreateUserRequest request) {

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

    User savedUser = userRepository.save(user);

    return new CreateUserResponse(
        user.getId(),
        user.getEmail(),
        user.getCreatedAt()
    );
  }
}
