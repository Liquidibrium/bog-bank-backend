package com.example.demo.controller;

import com.example.demo.model.UserDto;
import com.example.demo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
        Optional<UserDto> userDtoOptional = userService.getUserDtoByUsername(username);
        return userDtoOptional.map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


}
