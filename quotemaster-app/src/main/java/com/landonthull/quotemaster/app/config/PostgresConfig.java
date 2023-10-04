package com.landonthull.quotemaster.app.config;

import com.landonthull.quotemaster.core.customer.port.in.CustomerRepository;
import com.landonthull.quotemaster.core.customer.port.out.CustomerService;
import com.landonthull.quotemaster.core.user.port.in.PasswordEncoder;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import com.landonthull.quotemaster.core.user.port.out.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnProperty(name = "myapp.database.type", havingValue = "postgres")
@EnableJpaRepositories(basePackages = "com.landonthull.quotemaster.infrastructure.postgres")
@EntityScan(basePackages = "com.landonthull.quotemaster.infrastructure.postgres")
@ComponentScan(basePackages = "com.landonthull.quotemaster.infrastructure.postgres")
public class PostgresConfig {

  @Bean
  public UserService getUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserService(userRepository, passwordEncoder);
  }

  @Bean
  public CustomerService getCustomerService(CustomerRepository customerRepository) {
    return new CustomerService(customerRepository);
  }
}
