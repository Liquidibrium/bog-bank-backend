package ge.bog.bank.backend.controller;

import ge.bog.bank.backend.exception.InvalidMailException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.UserDto;
import ge.bog.bank.backend.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
        try {
            UserDto userDto = userService.deleteUserByUsername(username);
            log.info("successfully deleted user : %s".formatted(username));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.warn("user not found by username: %s".formatted(username));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("DELETE user %s | error %s".formatted(username, e.getMessage()));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        } catch (Exception e) {
            log.error("GET user by %s | error %s".formatted(id, e.getMessage()));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        return getUserDtoResponseEntity(newUser, userService);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        try {
            UserDto userDto = userService.updateUser(user);
            log.info("updated user: %s".formatted(user.getUsername()));
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            log.warn("could not update user: %s".formatted(user.getUsername()));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @NotNull
    protected static ResponseEntity<UserDto> getUserDtoResponseEntity(@RequestBody UserDto newUser,
                                                                      UserService userService) {
        try {
            UserDto createdUserDto = userService.createUser(newUser);
            log.info("created new user: %s".formatted(newUser.getUsername()));
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        } catch (InvalidMailException e) {
            log.warn("invalid email(%s) for user%s".formatted(newUser.getEmail(), newUser.getUsername()));
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.warn("could not create new user %s".formatted(newUser.getUsername()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
