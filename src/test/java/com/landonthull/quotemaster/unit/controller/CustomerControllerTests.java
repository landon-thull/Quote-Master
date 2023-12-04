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
import com.landonthull.quotemaster.controller.CustomerController;
import com.landonthull.quotemaster.domain.Customer;
import com.landonthull.quotemaster.dto.CreateCustomerRequest;
import com.landonthull.quotemaster.repository.QuoteRepository;
import com.landonthull.quotemaster.service.CustomerService;
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
@WebMvcTest(controllers = CustomerController.class)
@ContextConfiguration(classes = {CustomerController.class, JwtAuthenticationFilter.class, JwtTokenUtil.class})
@EnableMethodSecurity
public class CustomerControllerTests {

  @MockBean
  private CustomerService customerService;

  @MockBean
  private QuoteRepository quoteRepository;

  @MockBean
  private UserDetail userDetail;

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @WithMockUser(authorities = "SALES_REPRESENTATIVE")
  void createCustomer_validRequest_returnCreatedCustomerId() throws Exception {
    final Long ID = 1L;
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());

    final Customer toReturn = new Customer();
    toReturn.setId(ID);
    toReturn.setCreatedAt(CREATED_AT);

    when(customerService.createCustomer(any(CreateCustomerRequest.class)))
        .thenReturn(toReturn);

    final CreateCustomerRequest request = new CreateCustomerRequest(
        "Customer",
        null,
        "Industry"
    );


    mockMvc.perform(post("/customers")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.createdAt").exists());
  }

  @Test
  @WithMockUser(authorities = "REPORTING_USER")
  void createCustomer_invalidAuthority_returnForbidden() throws Exception {

    final CreateCustomerRequest request = new CreateCustomerRequest(
        "Customer",
        null,
        "Industry"
    );

    mockMvc.perform(post("/customers")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(authorities = "SALES_REPRESENTATIVE")
  void createCustomer_invalidRequest_returnBadRequest() throws Exception {
    final CreateCustomerRequest request = new CreateCustomerRequest(
        // customer name is required
        "",
        null,
        "Industry"
    );

    mockMvc.perform(post("/customers")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }
}
