package com.example.demo.service.user;

import com.example.demo.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserDtoByUsername(String username);


}
