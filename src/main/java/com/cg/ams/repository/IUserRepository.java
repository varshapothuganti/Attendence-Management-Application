package com.cg.ams.repository;

import com.cg.ams.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}