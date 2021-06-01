package com.example.demo.controller;

import com.example.demo.model.UserDto;
import com.example.demo.service.registration.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        UserDto createdUserDto = null;
        try {
            createdUserDto = registrationService.createUser(newUser);
        } catch (Exception e){
        }

        return new ResponseEntity<>(createdUserDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<String> getRegistrationString() {
        return new ResponseEntity<>("Please Register", HttpStatus.OK);
    }
}
