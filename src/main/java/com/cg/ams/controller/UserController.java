package com.cg.ams.controller;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.dto.UserOutputDTO;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	// Generic Success Message
	static final String GEN_SUCCESS_MESSAGE = "Option successful!";

	@Autowired
	IUserService userService;

	@PostMapping(path = "/add")
	ResponseEntity<String> addUser(@Valid @RequestBody UserInputDTO userInputDTO) {
		long id = userService.add(userInputDTO);
		String returnMsg = "User added with ID: ";

		return new ResponseEntity<>(returnMsg + id, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateUser(@Valid @RequestBody UserEntity userEntity) {
		userService.update(userEntity);

		return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	ResponseEntity<String> deleteUser(@Valid @RequestBody UserEntity userEntity) {
		userService.delete(userEntity);

		return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
	}

	@GetMapping(path = "/login/{login}")
	ResponseEntity<UserOutputDTO> getUserByLogin(@PathVariable("login") String login) {
		return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}")
	ResponseEntity<UserOutputDTO> getUserById(@PathVariable("id") long id) {
		return new ResponseEntity<>(userService.findByPk(id), HttpStatus.OK);
	}

	@GetMapping(path = "/search")
	ResponseEntity<List<UserOutputDTO>> search(@RequestParam("name") String name) {

		return new ResponseEntity<>(userService.search(name), HttpStatus.OK);
	}

	@GetMapping(path = "/search/{name}")
	ResponseEntity<List<UserOutputDTO>> search(@PathVariable String name,
			@RequestParam(value = "page", defaultValue = "0") int pageNo,
			@RequestParam(value = "size", defaultValue = "10") int pageSize) {
		return new ResponseEntity<>(userService.search(name, pageNo, pageSize), HttpStatus.OK);
	}

	@PostMapping(path = "/authenticate")
	ResponseEntity<UserOutputDTO> authenticate(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<>(userService.authenticate(userEntity), HttpStatus.OK);
	}

	@PatchMapping(path = "/change-password/{id}")
	ResponseEntity<String> changePassword(@PathVariable("id") long id, @RequestParam("oldPassword") String oldPassword,
			@RequestBody String newPassword) {
		userService.changePassword(id, oldPassword, newPassword);
		return new ResponseEntity<>("Password Changed Successfully!", HttpStatus.OK);
	}

	@PostMapping(path = "/register")
	ResponseEntity<String> registerUser(@Valid @RequestBody UserInputDTO userInputDTO) {
		long id = userService.registerUser(userInputDTO);
		return new ResponseEntity<>("User registered with id: " + id, HttpStatus.OK);
	}

	@PatchMapping(path = "/forgot-password/{login}")
	ResponseEntity<String> forgetPassword(@PathVariable("login") String login, @RequestBody String newPassword) {
		userService.forgetPassword(login, newPassword);
		return new ResponseEntity<>(GEN_SUCCESS_MESSAGE, HttpStatus.OK);
	}
}
