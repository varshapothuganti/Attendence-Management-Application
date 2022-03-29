package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.dto.UserOutputDTO;
import com.cg.ams.entity.UserEntity;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserServiceImpl userService;

	@Test
	void addTest() { // Just Tested Once (don't want to add records to DB)
		UserInputDTO user = new UserInputDTO();
		user.setFirstName("Viola");
		user.setLastName("Herrmann");
		user.setLogin("purplemeercat202");
		user.setPassword("dickens12345");
		user.setConfirmPassword("dickens12345");
		user.setGender("female");
		user.setDob(new Date());
		user.setRoleId(1);

		long prevCount = userService.count();
		userService.add(user);

		assertEquals(prevCount + 1, userService.count());
	}

	@Test
	void updateTest() {
		long testId = 3; // Hard coded
		UserEntity userEntity = userService.getUserById(testId);

		// Updating value
		String newLogin = "newloginid";
		userEntity.setLogin(newLogin);
		userService.update(new UserInputDTO(userEntity));

		UserOutputDTO updatedUser = userService.findByPk(testId);

		assertEquals(newLogin, updatedUser.getLogin());
	}

	@Test
	void deleteTest() {
		long beforeDeleteCount = userService.count();
		long testId = 4;
		UserEntity dbUser = userService.getUserById(testId);

		userService.delete(new UserInputDTO(dbUser));

		long afterDeleteCount = userService.count();

		assertEquals(beforeDeleteCount, afterDeleteCount + 1);
	}

	@Test
	void findByLogin() {
		String login = "pduvvuri";
		long id = 28;
		UserOutputDTO user = userService.findByLogin(login);

		assertEquals(id, user.getId());
	}

	@Test
	void findByPkTest() {
		long testId = 1; // entity name 'Phanindra'
		UserOutputDTO user = userService.findByPk(testId);

		assertEquals("phanindra", user.getFirstName());
		assertEquals("duvvuri", user.getLastName());
	}

	@Test
	void searchTest() {
		String searchTerm = "phani";
		List<UserOutputDTO> users = userService.search(searchTerm);
		long cnt = 3;

		assertEquals(cnt, users.size());
	}

	@Test
	void changePasswordTest() {
		String newPassword = "phanindra@123456";
		long testId = 1;
		UserEntity dbUser = userService.getUserById(testId);
		String oldPassword = dbUser.getPassword();

		userService.changePassword(testId, oldPassword, newPassword);
		UserEntity updatedUser = userService.getUserById(testId);

		assertEquals(newPassword, updatedUser.getPassword());

	}

	@Test
	void forgetPasswordTest() {
		long testId = 28;
		String newPassword = "phanindra@duvvuri";

		userService.forgetPassword("pduvvuri", newPassword);

		UserEntity updatedUser = userService.getUserById(testId);

		assertEquals(newPassword, updatedUser.getPassword());
	}
}