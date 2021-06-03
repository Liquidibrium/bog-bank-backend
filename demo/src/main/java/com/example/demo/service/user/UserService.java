package com.example.demo.service.user;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserDtoByUsername(String username) throws UserNotFoundException;

    UserDto getUserDtoById(Long id) throws UserNotFoundException;

    UserDto createUser(UserDto newUser) throws UserAlreadyExistsException;

    UserDto updateUser(UserDto user) throws UserNotFoundException;

    UserDto deleteUserByUsername(String username) throws UserNotFoundException;
}
