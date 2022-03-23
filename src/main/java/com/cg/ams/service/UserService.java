package com.cg.ams.service;

import com.cg.ams.entity.UserEntity;

import java.util.List;

public interface UserService {
    long add(UserEntity entity);
    void update(UserEntity entity);
    void delete(UserEntity entity);
    UserEntity findByLogin(String loginId);
    UserEntity findByPk(long id); // find by id (pk - primary key)
    List<UserEntity> search(UserEntity entity, long pageNo, int pageSize);
    List<UserEntity> search(UserEntity entity);
    UserEntity authenticate(UserEntity entity);
    boolean changePassword(Long id, String oldPassword, String newPassword);
    long registerUser(UserEntity entity);
    boolean forgetPassword(String login);
}