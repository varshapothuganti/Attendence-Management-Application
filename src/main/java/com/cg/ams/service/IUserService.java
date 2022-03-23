package com.cg.ams.service;

import com.cg.ams.bean.User;

import java.util.List;

public interface IUserService {
    long add(User entity);

    void update(User entity);

    void delete(User entity);

    User findByLogin(String loginId);

    User findByPk(long id); // find by id (pk - primary key)

    List<User> search(User entity, long pageNo, int pageSize);

    List<User> search(User entity);

    User authenticate(User entity);

    boolean changePassword(Long id, String oldPassword, String newPassword);

    long registerUser(User entity);

    boolean forgetPassword(String login, String newPassword);
}