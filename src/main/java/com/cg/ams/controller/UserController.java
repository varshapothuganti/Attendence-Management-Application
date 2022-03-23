package com.cg.ams.controller;

import com.cg.ams.bean.User;
import com.cg.ams.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    // Generic Success Message
    static final String GEN_SUCCESS_MESSAGE = "Option successful!";

    @Autowired
    IUserService userService;

    @PostMapping(path = "/add")
    ResponseEntity<String> addUser(@RequestBody User user) {
        long id = userService.add(user);
        String returnMsg = "User added with ID: ";

        return new ResponseEntity<>(returnMsg + id, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.update(user);

        return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.delete(user);

        return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping(path = "/login/{login}")
    ResponseEntity<User> getUserByLogin(@PathVariable("login") String login) {
        return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.findByPk(id), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    ResponseEntity<List<User>> search(@RequestBody User user) {
        return new ResponseEntity<>(userService.search(user), HttpStatus.OK);
    }

    // TODO add path
    ResponseEntity<List<User>> search(User entity, long pageNo, int pageSize) {
        return new ResponseEntity<>(userService.search(entity, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "/authenticate")
    ResponseEntity<User> authenticate(@RequestBody User user) {
        return new ResponseEntity<>(userService.authenticate(user), HttpStatus.OK);
    }

    @PatchMapping(path = "/change-password/{id}")
    ResponseEntity<String> changePassword(@PathVariable("id") long id, @RequestParam("oldPassword") String oldPassword, @RequestBody String newPassword) {
        userService.changePassword(id, oldPassword, newPassword);
        return new ResponseEntity<>("Password Changed Successfully!", HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    ResponseEntity<String> registerUser(@RequestBody User user) {
        long id = userService.registerUser(user);
        return new ResponseEntity<>("User registered with id: " + id, HttpStatus.OK);
    }

    @PatchMapping(path = "/forgot-password/{login}")
    ResponseEntity<String> forgetPassword(@PathVariable("login") String login, @RequestBody String newPassword) {
        userService.forgetPassword(login, newPassword);
        return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
