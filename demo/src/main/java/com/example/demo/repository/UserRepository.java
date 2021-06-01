package com.example.demo.repository;

import com.example.demo.entitiy.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(@NotNull Long aLong);

    Optional<UserEntity> findByUsername(@NotNull String username);

    List<UserEntity> findAll();

}
