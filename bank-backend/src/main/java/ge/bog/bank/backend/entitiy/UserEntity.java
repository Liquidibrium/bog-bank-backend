package ge.bog.bank.backend.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.bog.bank.backend.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
        })
public class UserEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_seq")
    @Column(name = "user_id",
            updatable = false)
    private Long userId;

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
    @JsonIgnore
    private String password;

    @Column(name = "email",
            nullable = false
    )
    private String email;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    @RestResource(path = "libraryAddress", rel = "address")
    // cascade
    @JsonIgnore
    private Set<AccountEntity> accountSet = new HashSet<>();


    public UserEntity(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
    }

    public void setUserDto(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
    }

    public void addAccount(AccountEntity accountEntity) {
        accountSet.add(accountEntity);
    }

}
