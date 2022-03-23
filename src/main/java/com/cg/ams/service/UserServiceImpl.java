package com.cg.ams.service;

import com.cg.ams.entity.UserEntity;
import com.cg.ams.exception.UserAuthenticationException;
import com.cg.ams.exception.UserNotFoundException;
import com.cg.ams.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public long add(UserEntity entity) {
        if (entity.getPassword().equals(entity.getConfirmPassword()))
            userRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void update(UserEntity entity) {
        UserEntity user = this.findByPk(entity.getId());

        userRepository.save(user);
    }

    @Override
    public void delete(UserEntity entity) {
        UserEntity user = this.findByPk(entity.getId());

        userRepository.delete(user);
    }

    @Override
    public UserEntity findByLogin(String loginId) {
        return userRepository.findByLogin(loginId);
    }

    @Override
    public UserEntity findByPk(long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return user;

    }

    @Override
    public List<UserEntity> search(UserEntity entity, long pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<UserEntity> search(UserEntity entity) {
        long id = entity.getId();

        List<UserEntity> result = new ArrayList<>();
        result.add(this.findByPk(id));

        return result;
    }

    @Override
    public UserEntity authenticate(UserEntity userEntity) {
        UserEntity dbUserEntity = this.findByPk(userEntity.getId());

        if (!(dbUserEntity.getLogin().equals(userEntity.getLogin()) && dbUserEntity.getPassword().equals(userEntity.getPassword()))) {
            throw new UserAuthenticationException("Authentication Failed! Check username and password again!");
        }

        return userEntity;
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        UserEntity dbUserEntity = this.findByPk(id);

        oldPassword = cleanString(oldPassword);
        newPassword = cleanString(newPassword);


        if (dbUserEntity.getPassword().equals(oldPassword)) {
            // update password
            dbUserEntity.setPassword(newPassword);
            dbUserEntity.setConfirmPassword(newPassword);

            userRepository.save(dbUserEntity);

            return true;
        }

        throw new UserAuthenticationException("Old password did not match with our records!");
    }

    @Override
    public long registerUser(UserEntity userEntity) {
        return this.add(userEntity);
    }

    @Override
    public boolean forgetPassword(String login, String newPassword) {
        UserEntity dbUserEntity = this.findByLogin(login);

        newPassword = cleanString(newPassword);

        dbUserEntity.setPassword(newPassword);
        dbUserEntity.setConfirmPassword(newPassword);

        userRepository.save(dbUserEntity);

        return true;
    }

    public String cleanString(String str) {
        str = str.trim();
        str = str.startsWith("\"") ? str.substring(1) : str;
        str = str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;

        return str;
    }
}
