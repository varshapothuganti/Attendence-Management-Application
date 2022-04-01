package com.cg.ams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.dto.UserOutputDTO;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.PasswordDidnotMatchException;
import com.cg.ams.exception.UserAuthenticationException;
import com.cg.ams.exception.UserNotFoundException;
import com.cg.ams.repository.IUserRepository;

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

	private static String USER_NOT_FOUND_MSG = "User not found with id: ";

	/**
	 * Add a new entry into the database after checking the password. Throws
	 * PasswordDidnotMatchException if the password didnot match
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

		if (userRepository.existsById(userInputDTO.getId()))
			throw new DuplicateRecordException(" A User with ID: " + userInputDTO.getId() + " Already exists!");

		UserEntity user = new UserEntity(userInputDTO);

		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateRecordException("A User with Login ID: " + " already exists!");
		}
		return user.getId();
	}

	/**
	 * updates a row in the database. Throws UserNotFoundException if the row
	 * doesn't exist.
	 *
	 * @param entity
	 */
	@Override
	public void update(UserInputDTO entity) {
		// Get the row from the db
		if (!userRepository.existsById(entity.getId()))
			throw new UserNotFoundException(USER_NOT_FOUND_MSG + entity.getId());

		if (userRepository.existsByLogin(entity.getLogin())) {
			throw new DuplicateRecordException("A User with Login ID: " + entity.getId() + " already exists!");
		}

		UserEntity updatedUser = new UserEntity(entity);

		userRepository.save(updatedUser);
	}

	/**
	 * Deletes a row from the database. Throws UserNotFoundException if the row
	 * doesn't exist.
	 *
	 * @param entity
	 */
	@Override
	public void delete(UserInputDTO entity) {
		UserEntity user = this.getUserById(entity.getId());

		userRepository.delete(user);
	}

	/**
	 * Searches the dababase for a record based on the login ID. If the login ID is
	 * not found not is returned.
	 *
	 * @param loginId
	 * @return UserOutputDTO
	 */
	@Override
	public UserOutputDTO findByLogin(String loginId) {
		UserEntity userEntity = userRepository.findByLogin(loginId);
		return new UserOutputDTO(userEntity);
	}

	/**
	 * Searches the database for a record based on the Primary Key (id). Throws
	 * UserNotFoundException if not found.
	 *
	 * @param id
	 * @return UserEntity
	 */
	@Override
	public UserOutputDTO findByPk(long id) {

		return new UserOutputDTO(
				userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MSG + id)));

	}

	/**
	 * Searches the database based on part of a name. Returns result after
	 * paginating.
	 *
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return List<UserOutputDTO>
	 */
	@Override
	public List<UserOutputDTO> search(String name, int pageNo, int pageSize) {
		Pageable currentPage = PageRequest.of(pageNo, pageSize);

		return userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name, currentPage)
				.stream().map(user -> new UserOutputDTO(user)).collect(Collectors.toList());

	}

	/**
	 * Searches the database based on part of a name
	 *
	 * @param name
	 * @return List<UserOutputDTO>
	 */
	@Override
	public List<UserOutputDTO> search(String name) {
		return userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(name, name).stream()
				.map(user -> new UserOutputDTO(user)).collect(Collectors.toList());
	}

	/**
	 * Authenticates the user by checking equality of Login and password. Throws
	 * UserAuthenticationException if failed.
	 *
	 * @param userEntity
	 * @return UserOutputDTO
	 */
	@Override
	public UserOutputDTO authenticate(UserInputDTO userEntity) {
		UserEntity dbUserEntity = this.getUserById(userEntity.getId());

		if (!(dbUserEntity.getLogin().equals(userEntity.getLogin())
				&& dbUserEntity.getPassword().equals(userEntity.getPassword()))) {
			throw new UserAuthenticationException("Authentication Failed! Check username and password again!");
		}

		return new UserOutputDTO(dbUserEntity);
	}

	/**
	 * Changes the password of the user. Throws UserAuthenticationException if the
	 * old passwords didn't match.
	 *
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return boolean
	 */
	@Override
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		UserEntity dbUserEntity = this.getUserById(id);

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
		UserEntity dbUserEntity = userRepository.findByLogin(login);
		// TODO change back return type and enforce unique contraint

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

	public UserEntity getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MSG + id));
	}

}
