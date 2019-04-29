package com.company.firesale;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:public_application.properties")
public class FiresaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiresaleApplication.class, args);
    }

}
