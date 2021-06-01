package com.example.demo.service.registration;


import com.example.demo.entitiy.UserEntity;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
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
