package com.product.app.service.Service;

import com.product.app.service.Models.Users;
import com.product.app.service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Users userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // You can also load roles from the database and map them accordingly
        // In this example, we assume that the roles are stored as a comma-separated string.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) // Assume password is already encoded
                .authorities(Collections.singletonList(authority))
                .build();
    }
}
