package com.cg.ams.service;

import com.cg.ams.dto.UserInputDTO;
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
//    @Disabled
    void add() throws ParseException {  // Just Tested Once (don't want to add records to DB)
        UserInputDTO user = new UserInputDTO();
        user.setFirstName("Viola");
        user.setLastName("Herrmann");
        user.setLogin("purplemeercat202");
        user.setPassword("dickens12345");
        user.setGender("female");
        user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
//        user.setRoleId(1);

        long dbCount = userService.count();
        userService.add(user);

        assertEquals(dbCount + 1, userService.count());
    }

    @Test
    void updateTest() {
    	long testId = 3;  // Hard coded
        UserEntity dbUser = userService.findByPk(testId);  

        // Updating value
        String newLogin = "newloginid";
        dbUser.setLogin(newLogin);
        userService.update(dbUser);

        UserEntity updatedUser = userService.findByPk(testId);

        assertEquals(newLogin, updatedUser.getLogin());
    }

    @Test
    @Disabled
    void deleteTest() {
        long beforeDeleteCount = userService.count();
        long testId = 10;
        UserEntity dbUser = userService.findByPk(testId);

        userService.delete(dbUser);

        long afterDeleteCount = userService.count();

        assertEquals(beforeDeleteCount, afterDeleteCount + 1);
    }

    @Test
    void findByLogin() {
        String login = "phanindra-duvvuri";
        long id = 1;
        String lastName = "Duvvuri";
        UserEntity user = userService.findByLogin(login);

        assertEquals(id, user.getId());
        assertEquals(lastName, user.getLastName());
    }

    @Test
    void findByPkTest() {
        long testId = 1;  // entity name 'Phanindra'
        UserEntity user = userService.findByPk(testId);

        assertEquals("Phanindra", user.getFirstName());
        assertEquals("Duvvuri", user.getLastName());
    }

    @Test
    void searchTest() {
        List<UserEntity> users = userService.search("Phan");
        long cnt = 2;

        assertEquals(cnt, users.size());
    }

    @Test
    void changePasswordTest() {
        String newPassword = "phanindra@123456";
        long testId = 1;
        UserEntity dbUser = userService.findByPk(testId);
        String oldPassword = dbUser.getPassword();

        userService.changePassword(testId, oldPassword, newPassword);
        UserEntity updatedUser = userService.findByPk(testId);

        assertEquals(newPassword, updatedUser.getPassword());

    }

    @Test
    void forgetPasswordTest() {
    	long testId = 1;
        String newPassword = "phanindra@duvvuri";

        userService.forgetPassword("phanindra-duvvuri", newPassword);

        UserEntity updatedUser = userService.findByPk(testId);

        assertEquals(newPassword, updatedUser.getPassword());
    }
}