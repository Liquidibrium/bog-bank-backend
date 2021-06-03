package com.example.demo.service.user;

import com.example.demo.entitiy.UserEntity;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
    public UserDto getUserDtoByUsername(@NotNull String username) throws UserNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            return UserDto.entityToDto(userEntityOptional.get());
        }
        throw new UserNotFoundException(username);
    }

    @Override
    public UserDto getUserDtoById(@NotNull Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return UserDto.entityToDto(userEntityOptional.get());
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public UserDto createUser(@NotNull UserDto newUser) {
        UserEntity entity = new UserEntity(newUser);
        try {
            UserEntity user = userRepository.save(entity);
            return UserDto.entityToDto(user);

        } catch (Exception e) {
            log.warn("could not create user ");
            throw new UserAlreadyExistsException(newUser.getUsername());
        }
    }

    @Override
    public UserDto updateUser(@NotNull UserDto userDto) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDto.getUsername());
        UserEntity userEntity;
        if (userEntityOptional.isPresent()) {
            userEntity = userEntityOptional.get();
            userEntity.setUserDto(userDto);
        } else {
            userEntity = new UserEntity(userDto);
        }
        userEntity = userRepository.save(userEntity);
        return UserDto.entityToDto(userEntity);
    }

    @Override
    public UserDto deleteUserByUsername(@NotNull String username) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if (userEntity.isPresent()) {
                userRepository.delete(userEntity.get());
                return UserDto.entityToDto(userEntity.get());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        log.warn("could not delete user : %s".formatted(username));
        throw new UserNotFoundException(username);
    }
}
