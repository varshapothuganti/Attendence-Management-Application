package com.cg.ams.service;

import com.cg.ams.bean.User;
import com.cg.ams.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public long add(User entity) {
        if (entity.getPassword().equals(entity.getConfirmPassword()))
            userRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void update(User entity) {
        User newUser = this.authenticate(entity);

        userRepository.save(newUser);
    }

    @Override
    public void delete(User entity) {
        User newUser = this.authenticate(entity);

        userRepository.delete(newUser);
    }

    @Override
    public User findByLogin(String loginId) {
        return userRepository.findByLogin(loginId);
    }

    @Override
    public User findByPk(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            // TODO throw exception
        }

        return userOptional.get();

    }

    @Override
    public List<User> search(User entity, long pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<User> search(User entity) {
        long id = entity.getId();

        List<User> result = new ArrayList<>();
        result.add(this.findByPk(id));

        return result;
    }

    @Override
    public User authenticate(User user) {
        User dbUser = this.findByPk(user.getId());

        if (!(dbUser.getLogin().equals(user.getLogin()) && dbUser.getPassword().equals(user.getPassword()))) {
            // TODO throw new AuthenticationException();
        }

        return user;
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User dbUser = this.findByPk(id);

        oldPassword = cleanString(oldPassword);
        newPassword = cleanString(newPassword);


        if (dbUser.getPassword().equals(oldPassword)) {
            // update password
            dbUser.setPassword(newPassword);
            dbUser.setConfirmPassword(newPassword);

            userRepository.save(dbUser);

            return true;
        }

        // TODO throw error
        return false;
    }

    @Override
    public long registerUser(User user) {
        return this.add(user);
    }

    @Override
    public boolean forgetPassword(String login, String newPassword) {
        User dbUser = this.findByLogin(login);

        newPassword = cleanString(newPassword);

        dbUser.setPassword(newPassword);
        dbUser.setConfirmPassword(newPassword);

        userRepository.save(dbUser);

        return true;
    }

    private String cleanString(String str) {
        str = str.trim();
        str = str.startsWith("\"") ? str.substring(1) : str;
        str = str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;

        return str;
    }
}
