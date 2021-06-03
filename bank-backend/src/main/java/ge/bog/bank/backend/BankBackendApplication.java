package ge.bog.bank.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankBackendApplication.class, args);
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
