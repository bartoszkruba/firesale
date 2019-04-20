package com.company.firesale.controller;

import com.company.firesale.data.entity.User;
import com.company.firesale.json_classes.UserJsonClass;
import com.company.firesale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String>
    registerNewUser(@Validated @RequestBody User user) {
        System.out.println(user.getUsername());
        return userService.registerNewUser(user);
    }

    @GetMapping("/{id}")
    public UserJsonClass getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
