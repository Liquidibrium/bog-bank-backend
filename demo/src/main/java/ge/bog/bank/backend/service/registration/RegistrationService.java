package ge.bog.bank.backend.service.registration;

import ge.bog.bank.backend.model.UserDto;
import org.springframework.stereotype.Service;


@Service
public interface RegistrationService {

    UserDto createUser(UserDto newUser);

}
