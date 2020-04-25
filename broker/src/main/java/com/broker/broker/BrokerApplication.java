package com.broker.broker;

import com.broker.broker.state.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BrokerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
