package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ams.entity.RoleEntity;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.repository.IUserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceMockitoTest {

	@InjectMocks
	UserServiceImpl userService;

	@MockBean
	IUserRepository userRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addTest() {
		RoleEntity role = new RoleEntity(100, "Admin", "Admin role");
		UserEntity user = new UserEntity(1000, "Tony", "Stark", "ironman", "iam.ironman", new Date(), "1122334455", role, "male", "profile-pic.jpg");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findById(1000L)).thenReturn(Optional.of(user));

		UserEntity user1 = userService.findByPk(1000);

		assertEquals("Phanindra", user1.getFirstName());
		assertEquals("pduvvuri", user1.getLogin());
	}

	@Test
	void updateTest() {
		RoleEntity role = new RoleEntity(100, "Admin", "Admin role");
		UserEntity user = new UserEntity(1000, "Tony", "Stark", "ironman", "iam.ironman", new Date(), "1122334455", role, "male", "profile-pic.jpg");
		
		Mockito.when(userRepository.findById(1000L)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		
		userService.update(user);
		
		assertEquals(user, userService.findByPk(1000));
	}
}
