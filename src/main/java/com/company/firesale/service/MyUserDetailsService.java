package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;

    private final UserService userService;

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    @Autowired
    public MyUserDetailsService(BCryptPasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        String[] userRoles = user.getRoles().stream().map(Role::getDescription).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(userRoles).build();
    }
}
