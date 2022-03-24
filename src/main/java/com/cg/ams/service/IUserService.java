package com.cg.ams.service;

import com.cg.ams.entity.UserEntity;
import com.cg.ams.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    long add(UserEntity entity);

    void update(UserEntity entity) throws UserNotFoundException;

    void delete(UserEntity entity) throws UserNotFoundException;

    UserEntity findByLogin(String loginId);

    UserEntity findByPk(long id); // find by id (pk - primary key)

    List<UserEntity> search(String name, long pageNo, int pageSize);

    List<UserEntity> search(String name);

    UserEntity authenticate(UserEntity entity);

    boolean changePassword(Long id, String oldPassword, String newPassword);

    long registerUser(UserEntity entity);

    boolean forgetPassword(String login, String newPassword);
}