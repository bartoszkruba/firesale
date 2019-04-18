package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public ResponseEntity<String> registerNewUser(User user) {

        if (user.getUsername() == null) {
            return new ResponseEntity<>("Username is required", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null) {
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exist", HttpStatus.CONFLICT);
        }

        Role userRole = roleRepository.findDistinctByDescription("USER");
        user.setId(null);
        user.setRoles(null);
        user.addRole(userRole);
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
