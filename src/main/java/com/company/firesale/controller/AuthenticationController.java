package com.company.firesale.controller;

import com.company.firesale.json_classes.UserDetailsJsonClass;
import com.company.firesale.json_classes.UserJsonClass;
import com.company.firesale.service.AuthenticationService;
import com.company.firesale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/roles")
    public String[] getRoles() {
        return authenticationService.getRoles();
    }

    @GetMapping("/account_details")
    public UserDetailsJsonClass getMyUserDetails(Principal principal) {
        return new UserDetailsJsonClass(userService.getUserByUsername(principal.getName()));
    }

    @GetMapping("/{id}")
    public UserJsonClass getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
