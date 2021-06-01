package com.example.demo.service.user;

import com.example.demo.entitiy.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceDB implements UserService {

    private final UserRepository userRepository;

    public UserServiceDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll().stream()
                .map(UserDto::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserDtoByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        return userEntityOptional.map(UserDto::entityToDto);
    }
}
