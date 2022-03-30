package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.dto.UserOutputDTO;
import com.cg.ams.entity.UserEntity;

/**
 * Test Case to check UserService
 * 
 * Before running this class run this query
 * 
delete from users where id = 10000; insert into users (id, first_name, last_name, login, password, gender, mobile_no, dob, profile_pic, role_id) values (10001, 'first1', 'last1', 'login1', '1234567890', 'male', '9876543210', NOW(), 'default-pic.jpg', 2), (10002, 'first2', 'last2', 'login2', '1234567890', 'male', '9876543210', NOW(), 'default-pic.jpg', 2), (10003, 'first3', 'last3', 'login3', '1234567890', 'male', '9876543210', NOW(), 'default-pic.jpg', 2), (10004, 'first4', 'last4', 'login4', '1234567890', 'male', '9876543210', NOW(), 'default-pic.jpg', 2), (10005, 'first5', 'last5', 'login5', '1234567890', 'male', '9876543210', NOW(), 'default-pic.jpg', 2);

 *
 * @author Phanidra
 */
@SpringBootTest
//@Sql(scripts = "/create-data.sql")
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {

	@Autowired
	UserServiceImpl userService;
	
	@BeforeAll
	void populateDataBase() {
		UserInputDTO userInputDTO2 = new UserInputDTO(10001, "first1", "last1", "login1", "password123", "password123", new Date(), "9876543210", "male", "default-pic.jpg", 3);
		UserInputDTO userInputDTO3 = new UserInputDTO(10002, "first2", "last2", "login2", "password123", "password123", new Date(), "9876543210", "male", "default-pic.jpg", 4);
		UserInputDTO userInputDTO4 = new UserInputDTO(10003, "first3", "last3", "login3", "password123", "password123", new Date(), "9876543210", "male", "default-pic.jpg", 5);
		UserInputDTO userInputDTO5 = new UserInputDTO(10004, "first4", "last4", "login4", "password123", "password123", new Date(), "9876543210", "male", "default-pic.jpg", 1);
		UserInputDTO userInputDTO6 = new UserInputDTO(10005, "first5", "last5", "login5", "password123", "password123", new Date(), "9876543210", "male", "default-pic.jpg", 2);
		
		userService.add(userInputDTO2);
		userService.add(userInputDTO3);
		userService.add(userInputDTO4);
		userService.add(userInputDTO5);
		userService.add(userInputDTO6);
	}
	
	@AfterAll
	
	void cleanDataBase() {
		UserEntity dbUser = userService.getUserById(10005);
		userService.delete(new UserInputDTO(dbUser));
		
		UserEntity dbUser1 = userService.getUserById(10001);
		userService.delete(new UserInputDTO(dbUser1));
		
		UserEntity dbUser11 = userService.getUserById(10002);
		userService.delete(new UserInputDTO(dbUser11));
		
		UserEntity dbUser111 = userService.getUserById(10003);
		userService.delete(new UserInputDTO(dbUser111));
		
		UserEntity dbUser1111 = userService.getUserById(10004);
		userService.delete(new UserInputDTO(dbUser1111));
	}


	/**
	 * Test case to test the add method.
	 * 
	 */
	@Test
	void addTest() { // Just Tested Once (don't want to add records to DB)
		UserInputDTO user = new UserInputDTO();
		user.setId(10000);
		user.setFirstName("first_name");
		user.setLastName("last_name");
		user.setLogin("unique_loginid");
		user.setPassword("password123");
		user.setConfirmPassword("password123");
		user.setGender("gender");
		user.setDob(new Date());
		user.setRoleId(1);

		long prevCount = userService.count();
		userService.add(user);

		assertEquals(prevCount + 1, userService.count());
	}

	/**
	 * Test case to check the update method
	 * 
	 * here checking for updating the loginid
	 */
	@Test
	void updateTest() {
		long testId = 10001; // Hard coded
		UserEntity userEntity = userService.getUserById(testId);

		// Updating value
		String newLogin = "new_unique_loginid";
		userEntity.setLogin(newLogin);
		userService.update(new UserInputDTO(userEntity));

		UserOutputDTO updatedUser = userService.findByPk(testId);

		assertEquals(newLogin, updatedUser.getLogin());
	}

	/**
	 * Test case for finding a user by their userID
	 */
	@Test
	void findByLogin() {
		String login = "login2";
		long id = 10002;
		UserOutputDTO user = userService.findByLogin(login);

		assertEquals(id, user.getId());
	}

	/**
	 * Test case to find a user by their primary key (id)
	 */
	@Test
	void findByPkTest() {
		long testId = 10003; // entity name 'Phanindra'
		UserOutputDTO user = userService.findByPk(testId);

		assertEquals("first3", user.getFirstName());
		assertEquals("last3", user.getLastName());
	}

	/**
	 * Search the database using the searchTerm matching either the first name or last name.
	 */
	@Test
	void searchTest() {
		String searchTerm = "first";
		List<UserOutputDTO> users = userService.search(searchTerm);
		users.forEach(System.out::println);
		long cnt = 6;

		assertEquals(cnt, users.size());
	}

	@Test
	void changePasswordTest() {
		String newPassword = "new_password";
		long testId = 10004;
		UserEntity dbUser = userService.getUserById(testId);
		String oldPassword = dbUser.getPassword();

		userService.changePassword(testId, oldPassword, newPassword);
		UserEntity updatedUser = userService.getUserById(testId);

		assertEquals(newPassword, updatedUser.getPassword());

	}

	@Test
	void forgetPasswordTest() {
		long testId = 10005;
		String newPassword = "phanindra@duvvuri";

		userService.forgetPassword("login5", newPassword);

		UserEntity updatedUser = userService.getUserById(testId);

		assertEquals(newPassword, updatedUser.getPassword());
	}

	@Test
	void deleteTest() {
		long beforeDeleteCount = userService.count();
		long testId = 10000;
		UserEntity dbUser = userService.getUserById(testId);

		userService.delete(new UserInputDTO(dbUser));

		long afterDeleteCount = userService.count();

		assertEquals(beforeDeleteCount, afterDeleteCount + 1);
	}
}