package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    private UserRepository repository;

    @Autowired
    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findDistinctFirstByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user);
    }

    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            repository.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private UserDetails toUserDetails(User user) {
        String[] userRoles = user.getRoles().stream().map(Role::getDescription).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(userRoles).build();
    }
}
