package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ams.dto.UserOutputDTO;
import com.cg.ams.entity.RoleEntity;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.repository.IRoleRepository;
import com.cg.ams.repository.IUserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceMockitoTest {

	@InjectMocks
	UserServiceImpl userService;

	@InjectMocks
	RoleServiceImpl roleService;

	@MockBean
	IUserRepository userRepository;

	@MockBean
	IRoleRepository roleRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addTest() {

	}

	@Test
	void updateTest() {

	}

	@Test
	void findByIDTest() {
		RoleEntity role = new RoleEntity(1, "ADMIN", "Administrator");
		UserEntity user = new UserEntity(1000l, "first1", "last1", "login1", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");

		Mockito.when(userRepository.findById(1000l)).thenReturn(Optional.of(user));

		UserOutputDTO userOutputDTO = userService.findByPk(1000l);

		assertEquals("first1", userOutputDTO.getFirstName());
		assertEquals("last1", userOutputDTO.getLastName());
	}
	
	@Test
	void findByLoginTest() {
		RoleEntity role = new RoleEntity(1, "ADMIN", "Administrator");
		UserEntity user = new UserEntity(1000l, "first1", "last1", "login1", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");

		Mockito.when(userRepository.findByLogin("login1")).thenReturn(user);

		UserOutputDTO userOutputDTO = userService.findByLogin("login1");
		
		assertEquals("login1", userOutputDTO.getLogin());
		assertEquals("first1", userOutputDTO.getFirstName());
	}

	@Test
	void searchTest() {
		RoleEntity role = new RoleEntity(1, "ADMIN", "Administrator");
		UserEntity user = new UserEntity(1000l, "first1", "last1", "login1", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");
		UserEntity user1 = new UserEntity(10002, "first2", "last2", "login2", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");
		UserEntity user2 = new UserEntity(10003, "first3", "last3", "login3", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");

		List<UserEntity> users = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);

		Mockito.when(userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("first", "first"))
				.thenReturn(users);

		List<UserOutputDTO> usersDtos = userService.search("first");

		assertEquals(3, usersDtos.size());

	}

	@Test
	void searchPageTest() {
		RoleEntity role = new RoleEntity(1, "ADMIN", "Administrator");
		UserEntity user = new UserEntity(1000l, "first1", "last1", "login1", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");
		UserEntity user1 = new UserEntity(10002, "first2", "last2", "login2", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");
		UserEntity user2 = new UserEntity(10003, "first3", "last3", "login3", "password@123", new Date(), "9876543210",
				role, "male", "default-pic.jpg");

		List<UserEntity> users = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);

		Pageable currentPage = PageRequest.of(0, 2);

		Mockito.when(userRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("first", "first",
				currentPage)).thenReturn(users.subList(0, 2));

		List<UserOutputDTO> usersDtos = userService.search("first", 0, 2);

		assertEquals(2, usersDtos.size());
	}
}
