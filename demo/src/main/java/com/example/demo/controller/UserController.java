package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserDto;
import com.example.demo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        try {
            List<UserDto> userDtoList = userService.getAllUsers();
            log.info("return info about all users ");
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } catch (Exception e) {
            log.warn("error while getting all users ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        try {
            UserDto userDto = userService.getUserDtoByUsername(username);
            log.info("returned user by username: %s".formatted(username));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn("not found user by username: %s".formatted(username));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{username}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        try {
            UserDto userDto = userService.getUserDtoById(id);
            log.info("returned user by ID: %d".formatted(id));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn("not found user by ID: %d".formatted(id));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        return getUserDtoResponseEntity(newUser, userService, log);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        try {
            UserDto userDto = userService.updateUser(user);
            log.info("updated user: %s".formatted(user.getUsername()));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            log.warn("could not update user");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @NotNull
    static ResponseEntity<UserDto> getUserDtoResponseEntity(@RequestBody UserDto newUser,
                                                            UserService userService,
                                                            Logger log) {
        try {
            UserDto createdUserDto = userService.createUser(newUser);
            log.info("creates new user: %s".formatted(newUser.getUsername()));
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

        } catch (Exception e) {
            log.warn("could not create new user");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
