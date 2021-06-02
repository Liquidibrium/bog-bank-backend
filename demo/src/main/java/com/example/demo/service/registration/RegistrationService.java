package com.example.demo.service.registration;

import com.example.demo.model.UserDto;
import org.springframework.stereotype.Service;


@Service
public interface RegistrationService {

    UserDto createUser(UserDto newUser);

}
