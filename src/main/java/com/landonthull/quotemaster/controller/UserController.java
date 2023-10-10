package com.landonthull.quotemaster.controller;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.dto.CreateUserResponse;
import com.landonthull.quotemaster.dto.GetCurrentUserResponse;
import com.landonthull.quotemaster.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMINISTRATOR')")
  public ResponseEntity<CreateUserResponse> createUser(
      @Valid @RequestBody CreateUserRequest request) throws URISyntaxException {
    User savedUser = userService.createUser(request);

    CreateUserResponse response = new CreateUserResponse(
        savedUser.getId(),
        savedUser.getEmail(),
        savedUser.getCreatedAt()
    );

    return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(response);
  }

  @GetMapping("/current")
  public ResponseEntity<GetCurrentUserResponse> getCurrentUser(HttpServletRequest request) {
    User user = userService.getCurrentUser(request);

    GetCurrentUserResponse response = new GetCurrentUserResponse(
        user.getId(),
        user.getEmail(),
        user.getRole(),
        user.getLastName(),
        user.getFirstName()
    );

    return ResponseEntity.ok().body(response);
  }
}
