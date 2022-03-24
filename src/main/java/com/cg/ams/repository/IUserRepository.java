package com.cg.ams.repository;

import com.cg.ams.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

    List<UserEntity> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstname, String lastname);

    List<UserEntity> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstname, String lastname, Pageable pageable);


}