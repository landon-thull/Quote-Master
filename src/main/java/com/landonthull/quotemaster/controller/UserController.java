package com.landonthull.quotemaster.controller;

import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.dto.CreateUserResponse;
import com.landonthull.quotemaster.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<CreateUserResponse> createUser(
      @Valid @RequestBody CreateUserRequest request) {
    CreateUserResponse response = userService.createUser(request);
    return ResponseEntity.created(URI.create("/users/" + response.getId())).body(response);
  }
}
