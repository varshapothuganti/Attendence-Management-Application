package com.example.sprint1.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.sprint1.bean.RoleEntity;
import com.example.sprint1.repository.IRoleRepository;

@ExtendWith(SpringExtension.class)
class RoleServiceMockitoTest {
	@InjectMocks
	RoleServiceImpl roleServ;

	@MockBean
	IRoleRepository roleRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void updateRole() {
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.getById(role.getId())).thenReturn(role);
		Mockito.when(roleRepo.save(role)).thenReturn(role);
		RoleEntity result = roleServ.updateRole(role);
		assertEquals(1, result.getId());
		assertEquals("ADMIN", result.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", result.getDescription());
	}

	@Test
	void deleteRole() {
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.getById(role.getId())).thenReturn(role);
//		Mockito.when(courseRepo.delete(course)).thenReturn(Mockito.doNothing());
		RoleEntity result= roleServ.deleteRole(role);
		assertEquals(1, result.getId());
		assertEquals("ADMIN", result.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", result.getDescription());
	}

	@Test
	void addRole() {
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.save(role)).thenReturn(role);
		String s = roleServ.addRole(role);
		assertEquals("Role Added Successfully", s);
	}

	@Test
	void findRoleByName() {
		String s = new String("ADMIN");
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.findByName(s)).thenReturn(role);
		RoleEntity role1 = roleServ.getRoleByName(s);
		assertEquals(1, role1.getId());
		assertEquals("ADMIN", role1.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", role1.getDescription());
	}

	@Test
	void findRoleById() {
		long input = 1;
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.findById(input)).thenReturn(Optional.of(role));
		RoleEntity role1 = roleServ.getRoleById(input);
		assertEquals(1, role1.getId());
		assertEquals("ADMIN", role1.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", role1.getDescription());
		}

	@Test
	void deleteById() {
		long input = 5;
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.findById(input)).thenReturn(Optional.of(role));
		RoleEntity role1 = roleServ.deleteRoleById(input);
		assertEquals(1, role1.getId());
		assertEquals("ADMIN", role1.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", role1.getDescription());
	}

	@Test
	void deleteByName() {
		String s = new String("ADMIN");
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.findByName(s)).thenReturn(role);
		RoleEntity role1 = roleServ.deleteRoleByName(s);
		assertEquals(1, role1.getId());
		assertEquals("ADMIN", role1.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", role1.getDescription());
	}
	
	@Test
	void updateRoleNameById() {
		long id=1;
		String name="Manager";
		RoleEntity role = new RoleEntity(1,"ADMIN","Admin is the activity or process of organizing an institution or organization");
		Mockito.when(roleRepo.findById(id)).thenReturn(Optional.of(role));
		RoleEntity role1 = roleServ.updateRoleNameById(id, name);
		assertEquals(1, role1.getId());
		assertEquals("Manager", role1.getName());
		assertEquals("Admin is the activity or process of organizing an institution or organization", role1.getDescription());
	}
	
//	@Test
//	void getAllRoles() {
//		List<CourseEntity> clist=new ArrayList<>();
//		CourseEntity course1 = new CourseEntity(5, "Java", "Java is a Programming Language");
//		CourseEntity course2 = new CourseEntity(6, "Python", "Python is a Programming Language");
//		CourseEntity course3 = new CourseEntity(7, "C++", "C++ is a Programming Language");
//		clist.add(course1);
//		clist.add(course2);
//		clist.add(course3);
//		Mockito.when(courseRepo.findAll()).thenReturn(clist);
//		List<CourseEntity> courses=courseServ.getAllCourses();
//		assertEquals(3,courses.size());
//	}


}
