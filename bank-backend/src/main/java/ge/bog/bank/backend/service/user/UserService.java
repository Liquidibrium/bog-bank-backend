package ge.bog.bank.backend.service.user;

import ge.bog.bank.backend.exception.UserAlreadyExistsException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserDtoByUsername(String username) throws UserNotFoundException;

    UserDto getUserDtoById(Long id) throws UserNotFoundException;

    UserDto createUser(UserDto newUser) throws UserAlreadyExistsException;

    UserDto updateUser(UserDto user) throws UserNotFoundException;

    UserDto deleteUserByUsername(String username) throws UserNotFoundException;
}
