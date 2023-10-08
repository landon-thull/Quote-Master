package com.landonthull.quotemaster.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.domain.UserRole;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.exception.ResourceAlreadyExistsException;
import com.landonthull.quotemaster.repository.UserRepository;
import com.landonthull.quotemaster.serviceimpl.UserServiceImpl;
import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @BeforeEach
  void setup() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
  }

  @Test
  void createUser_validRequest_returnSavedUser() {
    final Long ID = 1L;
    final String EMAIL = "email@email.com";
    final String PASSWORD = "hashedPassword";
    final boolean IS_ACTIVE = false;
    final UserRole ROLE = UserRole.REPORTING_USER;
    final String LAST_NAME = "Lastname";
    final String FIRST_NAME = "Firstname";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    when(userRepository.save(any(User.class)))
        .thenReturn(new User(
            ID, EMAIL, PASSWORD, IS_ACTIVE, ROLE, LAST_NAME, FIRST_NAME, CREATED_AT, UPDATED_AT
        ));

    User createdUser = userService.createUser(
        new CreateUserRequest(EMAIL, PASSWORD, LAST_NAME, FIRST_NAME)
    );

    assertNotNull(createdUser);
    assertEquals(createdUser.getId(), ID);
    assertEquals(createdUser.getEmail(), EMAIL);
    assertEquals(createdUser.getPassword(), PASSWORD);
    assertEquals(createdUser.isActive(), IS_ACTIVE);
    assertEquals(createdUser.getRole(), ROLE);
    assertEquals(createdUser.getLastName(), LAST_NAME);
    assertEquals(createdUser.getFirstName(), FIRST_NAME);
    assertEquals(createdUser.getCreatedAt(), CREATED_AT);
    assertEquals(createdUser.getUpdatedAt(), UPDATED_AT);
  }

  @Test
  void createUser_emailAlreadyExists_throwResourceAlreadyExistsException() {
    when(userRepository.existsByEmail(anyString())).thenReturn(true);

    final String EMAIL = "email@email.com";

    Exception exception = assertThrows(ResourceAlreadyExistsException.class,
        () -> userService.createUser(
        new CreateUserRequest(EMAIL, "rawPassword", "Last", "First")
    ));

    final String EXPECTED_MESSAGE = String.format("User already exists with email '%s'", EMAIL);

    assertEquals(exception.getMessage(), EXPECTED_MESSAGE);
  }
}

