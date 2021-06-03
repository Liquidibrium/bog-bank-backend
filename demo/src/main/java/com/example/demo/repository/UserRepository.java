package com.example.demo.repository;

import com.example.demo.entitiy.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @NotNull
    Optional<UserEntity> findById(@NotNull Long aLong);

    Optional<UserEntity> findByUsername(@NotNull String username);

    @NotNull
    List<UserEntity> findAll();

//    @Modifying TODO delete
    void deleteUserEntityByUsername(String username);

    void delete(UserEntity userEntity);
}
