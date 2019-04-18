package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<String> registerUser(User user) {

        if (user.getUsername() == null) {
            return new ResponseEntity<>("Username is required", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null) {
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        }

        Role userRole = roleRepository.findDistinctByDescription("USER");
        user.setId(null);
        user.setRoles(null);
        user.addRole(userRole);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
