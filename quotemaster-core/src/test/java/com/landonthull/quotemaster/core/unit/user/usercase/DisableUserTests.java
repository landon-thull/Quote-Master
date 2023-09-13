package com.landonthull.quotemaster.core.unit.user.usercase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.landonthull.quotemaster.core.common.domain.exception.PersistenceException;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.exception.UserNotFoundException;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUser;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserRequest;
import com.landonthull.quotemaster.core.user.usecase.disableuser.DisableUserResponse;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DisableUserTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private DisableUser disableUser;

  private static DisableUserRequest disableUserRequest;

  @BeforeAll
  public static void setup() {
    UUID id = UUID.randomUUID();
    disableUserRequest = new DisableUserRequest(id);
  }

  @Test
  public void DisableUser_Success_ReturnDisableUserResponse() {
    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(new User()));
    when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

    DisableUserResponse response = disableUser.execute(disableUserRequest);

    assertNotNull(response);
    assertEquals(response.getMessage(), "User successfully disabled");
  }

  @Test
  public void DisableUser_UserNotFound_ThrowUserNotFoundException() {
    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    UserNotFoundException exception = assertThrows(
        UserNotFoundException.class,
        () -> disableUser.execute(disableUserRequest)
    );

    assertEquals(
        exception.getMessage(),
        String.format("User not found with identifier '%s'",
            disableUserRequest.getUserId().toString()
        )
    );
  }

  @Test
  public void DisableUser_ErrorSaving_ThrowPersistenceException() {
    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(new User()));
    when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

    PersistenceException exception = assertThrows(
        PersistenceException.class,
        () -> disableUser.execute(disableUserRequest)
    );

    assertEquals(exception.getMessage(), "Error encountered while saving user data");
  }
}
