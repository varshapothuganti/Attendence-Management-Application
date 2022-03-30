package com.cg.ams.service;

import java.util.List;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.dto.UserOutputDTO;

/**
 * Defines all the operation that are supported by this service
 *
 * @author phanindra
 */
public interface IUserService {
	long add(UserInputDTO userInputDTO);

	void update(UserInputDTO entity);

	void delete(UserInputDTO entity);

	UserOutputDTO findByLogin(String loginId);

	UserOutputDTO findByPk(long id); // find by id (pk - primary key)

	List<UserOutputDTO> search(String name, int pageNo, int pageSize);

	List<UserOutputDTO> search(String name);

	UserOutputDTO authenticate(UserInputDTO entity);

	boolean changePassword(Long id, String oldPassword, String newPassword);

	long registerUser(UserInputDTO userInputDTO);

	boolean forgetPassword(String login, String newPassword);

	long count();
}