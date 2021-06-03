package com.example.demo.controller;

import com.example.demo.model.UserDto;
import com.example.demo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.controller.UserController.getUserDtoResponseEntity;

@RestController
@RequestMapping("/api/registration")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        return getUserDtoResponseEntity(newUser, userService, log);

    }

    @GetMapping
    public ResponseEntity<String> getRegistrationString() {
        return new ResponseEntity<>("<p>Please Register</p>", HttpStatus.OK);
    }
}
