package com.cg.ams.service;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.exception.PasswordDidnotMatchException;
import com.cg.ams.exception.UserAuthenticationException;
import com.cg.ams.exception.UserNotFoundException;
import com.cg.ams.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implements IUserService interface
 *
 * @author phanindra
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    IRoleService roleService;

    /**
     * Add a new entry into the database after checking the password.
     * Throws PasswordDidnotMatchException if the password didnot match
     *
     * @param user
     * @return id
     */
    @Override
    public long add(UserInputDTO userInputDTO) {
    	// Check if password == confirmPassword else throw exception
        if (!userInputDTO.getPassword().equals(userInputDTO.getConfirmPassword())) {
            throw new PasswordDidnotMatchException("Passwords did not match!");
        }
        
        UserEntity user = new UserEntity(userInputDTO, null);
        user.setRole(roleService.getRoleById(userInputDTO.getRoleId()));
        
        
        userRepository.save(user);
        return user.getId();
    }

    /**
     * updates a row in the database. Throws UserNotFoundException if the row doesn't exist.
     *
     * @param entity
     */
    @Override
    public void update(UserEntity entity) {
        this.findByPk(entity.getId());

        userRepository.save(entity);
    }

    /**
     * Deletes a row from the database. Throws UserNotFoundException if the row doesn't exist.
     *
     * @param entity
     */
    @Override
    public void delete(UserEntity entity) {
        UserEntity user = this.findByPk(entity.getId());

        userRepository.delete(user);
    }

    /**
     * Searches the dababase for a record based on the login ID. If the login ID is not found not is returned.
     *
     * @param loginId
     * @return UserEntity
     */
    @Override
    public UserEntity findByLogin(String loginId) {
        return userRepository.findByLogin(loginId);
    }

    /**
     * Searches the database for a record based on the Primary Key (id). Throws UserNotFoundException if not found.
     *
     * @param id
     * @return UserEntity
     */
    @Override
    public UserEntity findByPk(long id) {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    }

    /**
     * Searches the database based on part of a name. Returns result after paginating.
     *
     * @param name
     * @param pageNo
     * @param pageSize
     * @return List<UserEntity>
     */
    @Override
    public List<UserEntity> search(String name, int pageNo, int pageSize) {
        Pageable currentPage = PageRequest.of(pageNo, pageSize);

        return userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name, currentPage);
    }

    /**
     * Searches the database based on part of a name
     *
     * @param name
     * @return List<UserEntity>
     */
    @Override
    public List<UserEntity> search(String name) {
        return userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name);
    }

    /**
     * Authenticates the user by checking equality of Login and password. Throws UserAuthenticationException if failed.
     *
     * @param userEntity
     * @return UserEntity
     */
    @Override
    public UserEntity authenticate(UserEntity userEntity) {
        UserEntity dbUserEntity = this.findByPk(userEntity.getId());

        if (!(dbUserEntity.getLogin().equals(userEntity.getLogin()) && dbUserEntity.getPassword().equals(userEntity.getPassword()))) {
            throw new UserAuthenticationException("Authentication Failed! Check username and password again!");
        }

        return userEntity;
    }

    /**
     * Changes the password of the user. Throws UserAuthenticationException if the old passwords didn't match.
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return boolean
     */
    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        UserEntity dbUserEntity = this.findByPk(id);

        oldPassword = cleanString(oldPassword);
        newPassword = cleanString(newPassword);


        if (dbUserEntity.getPassword().equals(oldPassword)) {
            // update password
            dbUserEntity.setPassword(newPassword);

            userRepository.save(dbUserEntity);

            return true;
        }

        throw new UserAuthenticationException("Old password did not match with our records!");
    }

    /**
     * Registers the User (same as add).
     *
     * @param userEntity
     * @return long
     */
    @Override
    public long registerUser(UserInputDTO userInputDTO) {
        return this.add(userInputDTO);
    }

    /**
     * Resets the password by taking the login ID.
     *
     * @param login
     * @param newPassword
     * @return boolean
     */
    @Override
    public boolean forgetPassword(String login, String newPassword) {
        UserEntity dbUserEntity = this.findByLogin(login);

        newPassword = cleanString(newPassword);

        dbUserEntity.setPassword(newPassword);

        userRepository.save(dbUserEntity);

        return true;
    }

    /**
     * Returns the number of records in the database.
     *
     * @return long
     */
    @Override
    public long count() {
        return userRepository.count();
    }

    /**
     * Removes the leading and trailing whitespaces and quotation marks.
     *
     * @param str
     * @return String
     */
    public String cleanString(String str) {
        str = str.trim();
        str = str.startsWith("\"") ? str.substring(1) : str;
        str = str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;

        return str;
    }
}
