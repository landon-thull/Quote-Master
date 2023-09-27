package com.landonthull.quotemaster.app.controller;

import com.landonthull.quotemaster.app.dto.GetCurrentUserResponse;
import com.landonthull.quotemaster.app.service.UserService;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/current")
  public ResponseEntity<GetCurrentUserResponse> getCurrentUser(HttpServletRequest request) {
    User currentUser = userService.getCurrentUser(request);

    if (currentUser != null) {
      GetCurrentUserResponse response = new GetCurrentUserResponse(
          currentUser.getId(),
          currentUser.getEmail(),
          currentUser.getRole(),
          currentUser.getLastName(),
          currentUser.getFirstName()
      );

      return ResponseEntity.ok(response);
    }

    return ResponseEntity.badRequest().build();
  }
}
