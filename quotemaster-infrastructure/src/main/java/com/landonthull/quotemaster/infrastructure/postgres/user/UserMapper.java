package com.landonthull.quotemaster.infrastructure.postgres.user;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public JpaUserEntity toJpaEntity(final User user) {
    JpaUserEntity jpaEntity = new JpaUserEntity();
    jpaEntity.setId(user.getId());
    jpaEntity.setEmail(user.getEmail());
    jpaEntity.setActive(user.isActive());
    jpaEntity.setPassword(user.getPassword());
    jpaEntity.setLastName(user.getLastName());
    jpaEntity.setFirstName(user.getFirstName());
    jpaEntity.setCreatedAt(user.getCreatedAt());
    jpaEntity.setUpdatedAt(user.getUpdatedAt());
    
    return jpaEntity;
  }
  
  public User toDomainEntity(final JpaUserEntity jpaEntity) {
    User user = new User();
    user.setId(jpaEntity.getId());
    user.setEmail(jpaEntity.getEmail());
    user.setActive(jpaEntity.isActive());
    user.setPassword(jpaEntity.getPassword());
    user.setLastName(jpaEntity.getLastName());
    user.setFirstName(jpaEntity.getFirstName());
    user.setCreatedAt(jpaEntity.getCreatedAt());
    user.setUpdatedAt(jpaEntity.getUpdatedAt());
    
    return user;
  }
}
