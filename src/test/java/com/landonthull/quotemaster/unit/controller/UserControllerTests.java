package com.landonthull.quotemaster.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.landonthull.quotemaster.controller.UserController;
import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.domain.UserRole;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.dto.CreateUserResponse;
import com.landonthull.quotemaster.service.UserService;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @Test
  void createUser_validRequest_returnsCreatedUser() throws URISyntaxException {
    final Long ID = 1L;
    final String EMAIL = "email@email.com";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());

    when(userService.createUser(any(CreateUserRequest.class))).thenReturn(
        new User(
            ID,
            EMAIL,
            "hashedPassword",
            true,
            UserRole.SALES_REPRESENTATIVE,
            "Lastname",
            "Firstname",
            CREATED_AT,
            new Timestamp(System.currentTimeMillis())
        )
    );

    final CreateUserRequest request = new CreateUserRequest(
        EMAIL,
        "rawPassword",
        "Lastname",
        "Firstname"
    );

    ResponseEntity<CreateUserResponse> response = userController.createUser(request);

    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertEquals(response.getBody().getId(), ID);
    assertEquals(response.getBody().getEmail(), EMAIL);
    assertEquals(response.getBody().getCreatedAt(), CREATED_AT);
  }

}
