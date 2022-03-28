package com.cg.ams.service;

import com.cg.ams.entity.UserEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Disabled
class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    @Disabled
    void countTest() {  // Just Tested Once (hard coded values might change in the future)
        long currentCount = 38; //  Hard Coded

        assertEquals(currentCount, userService.count());
    }

    @Test
    @Disabled
    void add() throws ParseException {  // Just Tested Once (don't want to add records to DB)
        UserEntity user = new UserEntity();
        user.setFirstName("Viola");
        user.setLastName("Herrmann");
        user.setLogin("purplemeercat202");
        user.setPassword("dickens12345");
        user.setConfirmPassword("dickens12345");
        user.setGender("female");
        user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
        user.setRoleId(1);
        user.setProfilePic("default-pic.jpg");

        long dbCount = userService.count();
        userService.add(user);

        assertEquals(dbCount + 1, userService.count());
    }

    @Test
    void updateTest() {
    	long testId = 3;  // Hard coded
        UserEntity dbUser = userService.findByPk(testId);  

        // Updating value
        String newLogin = "phanindra-duvvuri";
        dbUser.setLogin(newLogin);
        userService.update(dbUser);

        UserEntity updatedUser = userService.findByPk(testId);

        assertEquals(newLogin, updatedUser.getLogin());
    }

    @Test
    @Disabled
    void deleteTest() {
        long beforeDeleteCount = userService.count();
        long testId = 6;
        UserEntity dbUser = userService.findByPk(testId);

        userService.delete(dbUser);

        long afterDeleteCount = userService.count();

        assertEquals(beforeDeleteCount, afterDeleteCount + 1);
    }

    @Test
    void findByLogin() {
        String login = "purplemeercat202";
        long id = 17;
        String lastName = "Herrmann";
        UserEntity user = userService.findByLogin(login);

        assertEquals(id, user.getId());
        assertEquals(lastName, user.getLastName());
    }

    @Test
    void findByPkTest() {
        long testId = 4;  // entity name 'Phanindra'
        UserEntity user = userService.findByPk(testId);

        assertEquals("Phanindra", user.getFirstName());
        assertEquals("Duvvuri", user.getLastName());
    }

    @Test
    void searchTest() {
        List<UserEntity> users = userService.search("syl");

        assertEquals(4, users.size());
    }

    @Test
    void changePasswordTest() {
        String newPassword = "phanindra@123";
        long testId = 5;
        UserEntity dbUser = userService.findByPk(testId);
        String oldPassword = dbUser.getPassword();

        userService.changePassword(2L, oldPassword, newPassword);
        UserEntity updatedUser = userService.findByPk(testId);

        assertEquals(newPassword, updatedUser.getPassword());

    }

    @Test
    void forgetPasswordTest() {
    	long testId = 4;
        @SuppressWarnings("unused")
		UserEntity dbUser = userService.findByPk(testId);
        String newPassword = "phanindra@duvvuri";

        userService.forgetPassword("phanindra-duvvuri", newPassword);

        UserEntity updatedUser = userService.findByPk(2);

        assertEquals(newPassword, updatedUser.getPassword());
    }
}