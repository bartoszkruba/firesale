package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.Category;
import com.company.firesale.data.entity.Role;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.data.repository.UserRepository;
import com.company.firesale.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDatabaseData implements CommandLineRunner {

    private UserRepository userRepository;
    private MyUserDetailsService myUserDetailsService;
    private RoleRepository roleRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public InsertDatabaseData(UserRepository userRepository, MyUserDetailsService userDetailsService, RoleRepository roleRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.myUserDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Roles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        //Categories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("All"));
        categories.add(new Category("Cars"));
        categories.add(new Category("Electronics"));
        categories.add(new Category("Furniture"));

        categories.forEach(category -> categoryRepository.save(category));


        //Users
        User user1 = new User();
        user1.setUsername("john69")
                .setPassword("password1234")
                .setEmail("john.doe@gmail.com")
                .setAddress("4323 Oak Lawn Ave")
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("(127)-963-1879")
                .addRole(adminRole)
                .addRole(userRole);

        myUserDetailsService.addUser(user1);

        User user2 = new User();
        user2.setUsername("mary111")
                .setPassword("mypassword")
                .setEmail("mary323@gmail.com")
                .setAddress("4968 Railroad St")
                .setFirstName("Mary")
                .setLastName("Poppins")
                .setPhoneNumber("(277)-505-2351")
                .addRole(userRole);

        myUserDetailsService.addUser(user2);

        User user3 = new User();
        user3.setUsername("ChrisL")
                .setPassword("wyatt")
                .setEmail("chris.lewis92@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Chris")
                .setLastName("Lewis")
                .setPhoneNumber("(893)-121-7525")
                .addRole(userRole);
        myUserDetailsService.addUser(user3);

        User user4 = new User();
        user4.setUsername("Morenorator")
                .setPassword("reptile")
                .setEmail("ruben.moreno87@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Ruben")
                .setLastName("Moreno")
                .setPhoneNumber("(247)-425-7475")
                .addRole(userRole);
        myUserDetailsService.addUser(user4);

        User user5 = new User();
        user5.setUsername("Cindirella")
                .setPassword("poopoo")
                .setEmail("ruben.moreno87@example.com")
                .setAddress("1164 Adelaide Ave")
                .setFirstName("Cindy")
                .setLastName("Stephens")
                .setPhoneNumber("(272)-121-1765")
                .addRole(userRole);
        myUserDetailsService.addUser(user5);

    }
}
