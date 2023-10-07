package com.landonthull.quotemaster.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.landonthull.quotemaster.domain.User;
import com.landonthull.quotemaster.domain.UserRole;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;

public class UserTests {

  @Test
  void noArgsConstructor_defaultFieldsSet() {
    User user = new User();

    assertTrue(user.isActive());
    assertEquals(user.getRole(), UserRole.SALES_REPRESENTATIVE);
  }

  @Test
  void someArgsConstructor_defaultFieldsSet() {
    final String EMAIL = "email@email.com";
    final String FIRST_NAME = "First";
    final String LAST_NAME = "Last";

    User user = new User(EMAIL, LAST_NAME, FIRST_NAME);

    assertTrue(user.isActive());
    assertEquals(user.getRole(), UserRole.SALES_REPRESENTATIVE);

    assertEquals(user.getEmail(), EMAIL);
    assertEquals(user.getLastName(), LAST_NAME);
    assertEquals(user.getFirstName(), FIRST_NAME);
  }

  @Test
  void allArgsConstructor_setsFieldsCorrectly() {
    final long ID = 1L;
    final String EMAIL = "email@email.com";
    final boolean IS_ACTIVE = false;
    final UserRole ROLE = UserRole.ADMINISTRATOR;
    final String LAST_NAME = "Last";
    final String FIRST_NAME = "First";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    User user = new User(
        ID,
        EMAIL,
        IS_ACTIVE,
        ROLE,
        LAST_NAME,
        FIRST_NAME,
        CREATED_AT,
        UPDATED_AT
    );

    assertEquals(user.getId(), ID);
    assertEquals(user.getEmail(), EMAIL);
    assertEquals(user.isActive(), IS_ACTIVE);
    assertEquals(user.getRole(), ROLE);
    assertEquals(user.getLastName(), LAST_NAME);
    assertEquals(user.getFirstName(), FIRST_NAME);
    assertEquals(user.getCreatedAt(), CREATED_AT);
    assertEquals(user.getUpdatedAt(), UPDATED_AT);
  }

  @Test
  void getters_setters_functionAsExpected() {
    User user = new User();

    final long ID = 1L;
    final String EMAIL = "email@email.com";
    final boolean IS_ACTIVE = false;
    final UserRole ROLE = UserRole.ADMINISTRATOR;
    final String LAST_NAME = "Last";
    final String FIRST_NAME = "First";
    final Timestamp CREATED_AT = new Timestamp(System.currentTimeMillis());
    final Timestamp UPDATED_AT = new Timestamp(System.currentTimeMillis());

    user.setId(ID);
    user.setEmail(EMAIL);
    user.setActive(IS_ACTIVE);
    user.setRole(ROLE);
    user.setLastName(LAST_NAME);
    user.setFirstName(FIRST_NAME);
    user.setCreatedAt(CREATED_AT);
    user.setUpdatedAt(UPDATED_AT);

    assertEquals(user.getId(), ID);
    assertEquals(user.getEmail(), EMAIL);
    assertEquals(user.isActive(), IS_ACTIVE);
    assertEquals(user.getRole(), ROLE);
    assertEquals(user.getLastName(), LAST_NAME);
    assertEquals(user.getFirstName(), FIRST_NAME);
    assertEquals(user.getCreatedAt(), CREATED_AT);
    assertEquals(user.getUpdatedAt(), UPDATED_AT);
  }
}
