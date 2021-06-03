package ge.bog.bank.backend.service.registration;


import ge.bog.bank.backend.entitiy.UserEntity;
import ge.bog.bank.backend.model.UserDto;
import ge.bog.bank.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceDB implements RegistrationService {
    private final UserRepository userRepository;

    public RegistrationServiceDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto newUser) {
        UserEntity entity = new UserEntity(newUser);
        UserEntity save = userRepository.save(entity);
        return UserDto.entityToDto(save);
    }


}
