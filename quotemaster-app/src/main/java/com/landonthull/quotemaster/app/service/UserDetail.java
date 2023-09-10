package com.landonthull.quotemaster.app.service;

import com.landonthull.quotemaster.core.user.domain.entity.User;
import com.landonthull.quotemaster.core.user.port.in.UserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetail implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetail(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findByEmail(email);
    if (userOptional.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    User user = userOptional.get();
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
    return new org.springframework.security.core.userdetails.User(
        email, user.getPassword(), authorities
    );
  }
}
