package com.company.firesale.config;

import com.company.firesale.data.repository.AuctionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {

    private final AuctionEntityRepository auctionEntityRepository;

    @Autowired
    public BeanConfiguration(AuctionEntityRepository auctionEntityRepository) {
        this.auctionEntityRepository = auctionEntityRepository;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
