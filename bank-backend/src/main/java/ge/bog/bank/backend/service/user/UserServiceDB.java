package ge.bog.bank.backend.service.user;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.UserEntity;
import ge.bog.bank.backend.exception.UserAlreadyExistsException;
import ge.bog.bank.backend.exception.UserNotFoundException;
import ge.bog.bank.backend.model.UserDto;
import ge.bog.bank.backend.repository.AccountRepository;
import ge.bog.bank.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceDB implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceDB(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
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
            accountRepository.save(new AccountEntity("GEl", user, BigDecimal.TEN));// ten GEL Gift!!
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
            userEntity = userRepository.save(userEntity);
        } else {
            userEntity = new UserEntity(userDto);
            userEntity = userRepository.save(userEntity);
            accountRepository.save(new AccountEntity("GEl", userEntity));
        }
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
