package com.landonthull.quotemaster.app.controller;

import com.landonthull.quotemaster.app.dto.GetCurrentUserResponse;
import com.landonthull.quotemaster.app.service.UserService;
import com.landonthull.quotemaster.app.util.LoggingUtil;
import com.landonthull.quotemaster.core.user.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final LoggingUtil loggingUtil = new LoggingUtil();

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/current")
  public ResponseEntity<GetCurrentUserResponse> getCurrentUser(HttpServletRequest request) {
    logger.info("Get current user request initiated by user {}", loggingUtil.getPrincipalEmail());
    User currentUser = userService.getCurrentUser(request);

    if (currentUser != null) {
      GetCurrentUserResponse response = new GetCurrentUserResponse(
          currentUser.getId(),
          currentUser.getEmail(),
          currentUser.getRole(),
          currentUser.getLastName(),
          currentUser.getFirstName()
      );

      logger.info("Returning user information for user {}", loggingUtil.getPrincipalEmail());
      return ResponseEntity.ok(response);
    }

    logger.info("Failed to retrieve user information for user {}, returning bad request",
        loggingUtil.getPrincipalEmail());
    return ResponseEntity.badRequest().build();
  }
}
