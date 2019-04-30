package com.company.firesale.service;

import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.data.repository.UserRepository;
import com.company.firesale.json_classes.UserJsonClass;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

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
        if (userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exist", HttpStatus.CONFLICT);
        }

        Role userRole = roleRepository.findDistinctByDescription("USER");
        user.setId(null);
        user.setRoles(new HashSet<>());
        user.addRole(userRole);
        user.setPassword(encoder.encode(user.getPassword()));

        saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public ResponseEntity<String> updateUser(User user) {
        if(userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername()) != null){
           saveUser(user);
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserJsonClass getUserById(Long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            return new UserJsonClass(u.get());
        } else {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findDistinctFirstByUsernameIgnoreCase(username);
    }
}
