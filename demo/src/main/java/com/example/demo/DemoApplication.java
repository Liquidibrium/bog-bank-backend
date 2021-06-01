package com.example.demo;

import com.example.demo.entitiy.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//        return args -> {
//            UserEntity userEntity = new UserEntity(
//                    "test_user_name",
//                    "test_first_name",
//                    "test_last_name",
//                    "test_password",
//                    "test_email"
//            );
//            userRepository.save(userEntity);
//        };
//    }
}
