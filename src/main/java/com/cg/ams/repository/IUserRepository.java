package com.cg.ams.repository;

import com.cg.ams.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}