package ge.bog.bank.backend.model;

import ge.bog.bank.backend.entitiy.UserEntity;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public static UserDto entityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword()) // TODO
                .email(userEntity.getEmail())
                .build();
    }

}
