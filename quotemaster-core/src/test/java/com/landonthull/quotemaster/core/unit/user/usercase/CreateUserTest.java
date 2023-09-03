package com.landonthull.quotemaster.core.unit.user.usercase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.landonthull.quotemaster.core.common.domain.exception.PersistenceException;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.exception.UserAlreadyExistsException;
import com.landonthull.quotemaster.core.user.port.PasswordEncoder;
import com.landonthull.quotemaster.core.user.port.UserRepository;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUser;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserRequest;
import com.landonthull.quotemaster.core.user.usecase.createuser.CreateUserResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private CreateUser createUser;

  private static CreateUserRequest request;

  @BeforeAll
  public static void setup() {
    request = new CreateUserRequest(
        "useremail@email.com",
        "unencodedPassword",
        "first",
        "user"
    );
  }

  @Test
  public void createUser_RequestValid_ReturnResponse() {
    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    when(userRepository.save(any())).thenReturn(new User());
    when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

    CreateUserResponse response = createUser.execute(request);

    assertEquals(response.getEmail(), request.getEmail());
    assertNotNull(response.getId());
    assertNotNull(response.getCreatedAt());
  }

  @Test
  public void createUser_UserAlreadyExists_ThrowUserAlreadyExistsException() {
    when(userRepository.existsByEmail(anyString())).thenReturn(true);

    assertThrows(UserAlreadyExistsException.class, () -> createUser.execute(request));
  }

  @Test
  public void createUser_ErrorSavingUser_ThrowPersistenceException() {
    when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Exception"));

    assertThrows(PersistenceException.class, () -> createUser.execute(request));
  }
}
