package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.Category;
import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDatabaseData implements CommandLineRunner {

    private UserService userService;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public InsertDatabaseData(UserService userService, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;

        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

        //Roles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);



        //Users
        User user1 = new User();
        user1.setUsername("john69")
                .setPassword(encoder.encode("password1234"))
                .setEmail("john.doe@gmail.com")
                .setAddress("4323 Oak Lawn Ave")
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("(127)-963-1879")
                .addRole(adminRole)
                .addRole(userRole);

        userService.saveUser(user1);

        User user2 = new User();
        user2.setUsername("mary111")
                .setPassword(encoder.encode("mypassword"))
                .setEmail("mary323@gmail.com")
                .setAddress("4968 Railroad St")
                .setFirstName("Mary")
                .setLastName("Poppins")
                .setPhoneNumber("(277)-505-2351")
                .addRole(userRole);

        userService.saveUser(user2);

        User user3 = new User();
        user3.setUsername("ChrisL")
                .setPassword(encoder.encode("wyatt"))
                .setEmail("chris.lewis92@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Chris")
                .setLastName("Lewis")
                .setPhoneNumber("(893)-121-7525")
                .addRole(userRole);
        userService.saveUser(user3);

        User user4 = new User();
        user4.setUsername("Morenorator")
                .setPassword(encoder.encode("reptile"))
                .setEmail("ruben.moreno87@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Ruben")
                .setLastName("Moreno")
                .setPhoneNumber("(247)-425-7475")
                .addRole(userRole);
        userService.saveUser(user4);

        User user5 = new User();
        user5.setUsername("Cindirella")
                .setPassword(encoder.encode("poopoo"))
                .setEmail("ruben.moreno87@example.com")
                .setAddress("1164 Adelaide Ave")
                .setFirstName("Cindy")
                .setLastName("Stephens")
                .setPhoneNumber("(272)-121-1765")
                .addRole(userRole);
        userService.saveUser(user5);

    }
}
