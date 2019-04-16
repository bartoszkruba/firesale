package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InsertDatabaseData implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public InsertDatabaseData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("john69");
        user1.setPassword("password1234");

        User user2 = new User();

        user2.setUsername("Mary111");
        user2.setPassword("mypassword");

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
