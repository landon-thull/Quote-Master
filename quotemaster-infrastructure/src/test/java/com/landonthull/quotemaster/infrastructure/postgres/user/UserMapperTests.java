package com.landonthull.quotemaster.infrastructure.postgres.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.domain.entity.UserRole;
import java.sql.Timestamp;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserMapperTests {

  private final UserMapper userMapper = new UserMapper();

  private static User user;
  private static JpaUserEntity jpaUser;

  @BeforeAll
  public static void setup() {
    UUID id = UUID.randomUUID();
    String email = "email@email.com";
    String password = "password123";
    boolean isActive = true;
    UserRole role = UserRole.SALES_REPRESENTATIVE;
    String lastName = "Lastname";
    String firstName = "Firstname";
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    user = new User(
        id, email, password, isActive, role, lastName, firstName, timestamp, timestamp
    );

    jpaUser = new JpaUserEntity(
        id, email, password, isActive, role, lastName, firstName, timestamp, timestamp
    );
  }

  @Test
  public void toJpaEntity_MappedCorrectly() {
    JpaUserEntity mappedEntity = userMapper.toJpaEntity(user);

    assertNotNull(mappedEntity);
    assertEquals(mappedEntity.getId(), jpaUser.getId());
    assertEquals(mappedEntity.getEmail(), jpaUser.getEmail());
    assertEquals(mappedEntity.getPassword(), jpaUser.getPassword());
    assertEquals(mappedEntity.isActive(), jpaUser.isActive());
    assertEquals(mappedEntity.getRole(), jpaUser.getRole());
    assertEquals(mappedEntity.getLastName(), jpaUser.getLastName());
    assertEquals(mappedEntity.getFirstName(), jpaUser.getFirstName());
    assertEquals(mappedEntity.getCreatedAt(), jpaUser.getCreatedAt());
    assertEquals(mappedEntity.getUpdatedAt(), jpaUser.getUpdatedAt());
  }

  @Test
  public void toDomainEntity_MappedCorrectly() {
    User mappedUser = userMapper.toDomainEntity(jpaUser);

    assertNotNull(mappedUser);
    assertEquals(mappedUser.getId(), user.getId());
    assertEquals(mappedUser.getEmail(), user.getEmail());
    assertEquals(mappedUser.getPassword(), user.getPassword());
    assertEquals(mappedUser.isActive(), user.isActive());
    assertEquals(mappedUser.getRole(), user.getRole());
    assertEquals(mappedUser.getLastName(), user.getLastName());
    assertEquals(mappedUser.getFirstName(), user.getFirstName());
    assertEquals(mappedUser.getCreatedAt(), user.getCreatedAt());
    assertEquals(mappedUser.getUpdatedAt(), user.getUpdatedAt());
  }

}
