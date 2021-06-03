package com.example.demo.entitiy;

import com.example.demo.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "username_unique", columnNames = "username")
        }
)
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_seq"
    )
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "username",
            nullable = false
    )
    private String username;

    @Column(name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(name = "password",
            nullable = false
    )
    private String password;

    @Column(name = "email",
            nullable = false
    )
    private String email;

    @Column(name = "balance",
            nullable = false
    )
    private Long balance;

    public UserEntity(String username,
                      String firstName,
                      String lastName,
                      String password,
                      String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.balance = 0L;
    }

    public UserEntity(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.balance = 0L;
    }

    public void setUserDto(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
    }
}
