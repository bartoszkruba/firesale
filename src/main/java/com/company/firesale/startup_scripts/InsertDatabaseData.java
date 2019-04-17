package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.UserRepository;
import com.company.firesale.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InsertDatabaseData implements CommandLineRunner {

    private UserRepository userRepository;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public InsertDatabaseData(UserRepository userRepository, MyUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.myUserDetailsService = userDetailsService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("john69")
                .setPassword("password1234")
                .setEmail("john.doe@gmail.com")
                .setAddress("4323 Oak Lawn Ave")
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("(127)-963-1879");
        myUserDetailsService.addUser(user1);

        User user2 = new User();
        user2.setUsername("mary111")
                .setPassword("mypassword")
                .setEmail("mary323@gmail.com")
                .setAddress("4968 Railroad St")
                .setFirstName("Mary")
                .setLastName("Poppins")
                .setPhoneNumber("(277)-505-2351");
        myUserDetailsService.addUser(user2);

        User user3 = new User();
        user3.setUsername("ChrisL")
                .setPassword("wyatt")
                .setEmail("chris.lewis92@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Chris")
                .setLastName("Lewis")
                .setPhoneNumber("(893)-121-7525");
        myUserDetailsService.addUser(user3);

        User user4 = new User();
        user4.setUsername("Morenorator")
                .setPassword("reptile")
                .setEmail("ruben.moreno87@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Ruben")
                .setLastName("Moreno")
                .setPhoneNumber("(247)-425-7475");
        myUserDetailsService.addUser(user4);

        User user5 = new User();
        user5.setUsername("Cindirella")
                .setPassword("poopoo")
                .setEmail("ruben.moreno87@example.com")
                .setAddress("1164 Adelaide Ave")
                .setFirstName("Cindy")
                .setLastName("Stephens")
                .setPhoneNumber("(272)-121-1765");
        myUserDetailsService.addUser(user5);

    }
}
