package com.landonthull.quotemaster.infrastructure.postgres.user;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresUserRepository implements UserRepository {

  private final UserMapper userMapper;

  private final JpaUserRepository jpaUserRepository;

  public PostgresUserRepository(UserMapper userMapper, JpaUserRepository jpaUserRepository) {
    this.userMapper = userMapper;
    this.jpaUserRepository = jpaUserRepository;
  }

  @Override
  public User save(User user) {
    JpaUserEntity jpaUserEntity = userMapper.toJpaEntity(user);
    JpaUserEntity savedJpaUserEntity = jpaUserRepository.save(jpaUserEntity);
    return userMapper.toDomainEntity(savedJpaUserEntity);
  }

  @Override
  public Optional<User> findById(UUID id) {
    Optional<JpaUserEntity> optionalJpaUserEntity = jpaUserRepository.findById(id);

    if (optionalJpaUserEntity.isEmpty()) return Optional.empty();

    JpaUserEntity jpaUserEntity = optionalJpaUserEntity.get();
    User foundUser = userMapper.toDomainEntity(jpaUserEntity);
    return Optional.of(foundUser);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    Optional<JpaUserEntity> optionalJpaUserEntity = jpaUserRepository.findByEmail(email);
    if (optionalJpaUserEntity.isEmpty()) return Optional.empty();

    JpaUserEntity jpaUserEntity = optionalJpaUserEntity.get();
    User foundUser = userMapper.toDomainEntity(jpaUserEntity);
    return Optional.of(foundUser);
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpaUserRepository.existsByEmail(email);
  }
}
