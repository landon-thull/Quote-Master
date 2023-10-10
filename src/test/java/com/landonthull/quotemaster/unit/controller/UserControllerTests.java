package com.landonthull.quotemaster.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landonthull.quotemaster.auth.JwtAuthenticationFilter;
import com.landonthull.quotemaster.auth.UserDetail;
import com.landonthull.quotemaster.controller.UserController;
import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.domain.UserRole;
import com.landonthull.quotemaster.dto.CreateUserRequest;
import com.landonthull.quotemaster.service.UserService;
import com.landonthull.quotemaster.util.JwtTokenUtil;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = {UserController.class, JwtAuthenticationFilter.class, JwtTokenUtil.class})
@EnableMethodSecurity
public class UserControllerTests {

  @MockBean
  private UserService userService;

  @MockBean
  private UserDetail userDetail;

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @WithMockUser(authorities = "ADMINISTRATOR")
  void createUser_validRequest_returnsCreatedUser() throws Exception {
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
        "Landon",
        "Thull"
    );

    mockMvc.perform(post("/users")
            .with(csrf())
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.email").value(EMAIL))
        .andExpect(jsonPath("$.createdAt").exists());
  }

  @Test
  @WithMockUser(authorities = "SALES_REPRESENTATIVE")
  void createUser_unauthorized_returnForbidden() throws Exception {
    final CreateUserRequest request = new CreateUserRequest(
        "email@email.com",
        "rawPassword",
        "Last",
        "First"
    );

    mockMvc.perform(post("/users")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(authorities = "ADMINISTRATOR")
  void createUser_invalidRequest_returnBadRequest() throws Exception {
    final CreateUserRequest request = new CreateUserRequest(
        "email.email.com",
        "rawPassword",
        "Last",
        "First"
    );

    mockMvc.perform(post("/users")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }
}
