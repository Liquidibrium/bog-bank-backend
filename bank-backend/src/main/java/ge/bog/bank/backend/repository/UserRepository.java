package ge.bog.bank.backend.repository;

import ge.bog.bank.backend.entitiy.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @NotNull
    Optional<UserEntity> findById(@NotNull Long aLong);

    Optional<UserEntity> findByUsername(@NotNull String username);

    @NotNull
    List<UserEntity> findAll();

    void delete(@NotNull UserEntity userEntity);
}
