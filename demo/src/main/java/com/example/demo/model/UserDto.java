package com.example.demo.model;

import com.example.demo.entitiy.UserEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public static UserDto entityToDto(UserEntity userEntity){
        return UserDto.builder()
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword()) // TODO
                .email(userEntity.getEmail())
                .build();
    }

}
