package ge.bog.bank.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.UserEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
//    @JsonIgnore
    private String password;
    private String email;
//    @JsonIgnore
//    private Set<AccountEntity> accountList;

    public static UserDto entityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword()) // TODO change to hash
                .email(userEntity.getEmail())
                .build();
    }

}
