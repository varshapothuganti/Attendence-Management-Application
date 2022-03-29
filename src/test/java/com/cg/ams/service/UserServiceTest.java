//package com.cg.ams.service;
//
//import com.cg.ams.dto.UserInputDTO;
//import com.cg.ams.dto.UserOutputDTO;
//import com.cg.ams.entity.UserEntity;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Disabled
//class UserServiceTest {
//
//	@Autowired
//	IUserService userService;
//
//	@Test
//	void addTest() throws ParseException { // Just Tested Once (don't want to add records to DB)
//		UserInputDTO user = new UserInputDTO();
//		user.setFirstName("Viola");
//		user.setLastName("Herrmann");
//		user.setLogin("purplemeercat202");
//		user.setPassword("dickens12345");
//		user.setConfirmPassword("dickens12345");
//		user.setGender("female");
//		user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
//		user.setRoleId(1);
//
//		long prevCount = userService.count();
//		userService.add(user);
//
//		assertEquals(prevCount + 1, userService.count());
//	}
//
//	@Test
//	void updateTest() {
//		long testId = 1; // Hard coded
//		UserOutputDTO dbUser = userService.findByPk(testId);
//
//		// Updating value
//		String newLogin = "newloginid";
//		dbUser.setLogin(newLogin);
//		userService.update(dbUser);
//
//		UserOutputDTO updatedUser = userService.findByPk(testId);
//
//		assertEquals(newLogin, updatedUser.getLogin());
//	}
//
//	@Test
//	void deleteTest() {
//		long beforeDeleteCount = userService.count();
//		long testId = 4;
//		UserOutputDTO dbUser = userService.findByPk(testId);
//
//		userService.delete(dbUser);
//
//		long afterDeleteCount = userService.count();
//
//		assertEquals(beforeDeleteCount, afterDeleteCount + 1);
//	}
//
//	@Test
//	void findByLogin() {
//		String login = "newloginid";
//		long id = 1;
//		String lastName = "duvvuri";
//		UserEntity user = userService.findByLogin(login);
//
//		assertEquals(id, user.getId());
//		assertEquals(lastName, user.getLastName());
//	}
//
//	@Test
//	void findByPkTest() {
//		long testId = 1; // entity name 'Phanindra'
//		UserEntity user = userService.findByPk(testId);
//
//		assertEquals("phanindra", user.getFirstName());
//		assertEquals("duvvuri", user.getLastName());
//	}
//
//	@Test
//	void searchTest() {
//		List<UserEntity> users = userService.search("Phan");
//		long cnt = 5;
//
//		assertEquals(cnt, users.size());
//	}
//
//	@Test
//	void changePasswordTest() {
//		String newPassword = "phanindra@123456";
//		long testId = 1;
//		UserEntity dbUser = userService.findByPk(testId);
//		String oldPassword = dbUser.getPassword();
//
//		userService.changePassword(testId, oldPassword, newPassword);
//		UserEntity updatedUser = userService.findByPk(testId);
//
//		assertEquals(newPassword, updatedUser.getPassword());
//
//	}
//
//	@Test
//	void forgetPasswordTest() {
//		long testId = 19;
//		String newPassword = "phanindra@duvvuri";
//
//		userService.forgetPassword("pduvvuri", newPassword);
//
//		UserEntity updatedUser = userService.findByPk(testId);
//
//		assertEquals(newPassword, updatedUser.getPassword());
//	}
//}