package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.model.UserDto;
import ge.bog.bank.backend.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        return UserController.getUserDtoResponseEntity(newUser, userService);

    }

    @GetMapping
    public ResponseEntity<String> getRegistrationString() {
        return new ResponseEntity<>("<p>Please Register</p>", HttpStatus.OK);
    }
}
