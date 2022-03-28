package com.cg.ams.service;

import java.util.List;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.UserEntity;

/**
 * Defines all the operation that are supported by this service
 *
 * @author phanindra
 */
public interface IUserService {
	long add(UserInputDTO userInputDTO);

	void update(UserEntity entity);

	void delete(UserEntity entity);

	UserEntity findByLogin(String loginId);

	UserEntity findByPk(long id); // find by id (pk - primary key)

	List<UserEntity> search(String name, int pageNo, int pageSize);

	List<UserEntity> search(String name);

	UserEntity authenticate(UserEntity entity);

	boolean changePassword(Long id, String oldPassword, String newPassword);

	long registerUser(UserInputDTO userInputDTO);

	boolean forgetPassword(String login, String newPassword);

	long count();
}