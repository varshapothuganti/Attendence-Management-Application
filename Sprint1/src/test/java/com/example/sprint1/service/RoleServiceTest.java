package com.example.sprint1.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sprint1.bean.RoleEntity;

@SpringBootTest
class RoleServiceTest {

	@Autowired
	IRoleService roleServ;
	
	@Test
	void addRole() {
		RoleEntity role=new RoleEntity(5,"HOD","Head of Department");
		String s=roleServ.addRole(role);
		assertEquals("Role Added Successfully",s);
	}
	
	@Test
	void updateRole() {
		RoleEntity role=new RoleEntity(4,"PRINCIPAL","Pricipal of Organization");
		RoleEntity result=roleServ.updateRole(role);
		assertEquals("Pricipal of Organization",result.getDescription());
	}
	
	@Test
	void updateNameById() {
		RoleEntity role=roleServ.updateRoleNameById(5,"Mounish");
		assertEquals("Head of Department",role.getDescription());
	}
	
//	@Test
//	void deleteRole() {
//		RoleEntity role=new RoleEntity(5,"Mounish","Head of Department");
//		RoleEntity result=roleServ.deleteRole(role);
//		assertEquals("Mounish",result.getName());
//		//assertEquals("Python ia a Programming Language",result.getDescription());
//		
//	}
	
	@Test
	void getByName() {
		RoleEntity role=roleServ.getRoleByName("USER");
        assertEquals(3,role.getId());
	}
	
	@Test
	void getById() {
		RoleEntity role=roleServ.getRoleById(4);
        assertEquals("PRINCIPAL",role.getName());
	}
	
	@Test
	void deleteByName() {
		RoleEntity course=roleServ.deleteRoleByName("PRINCIPAL");
        assertEquals(4,course.getId());
	}
	
	@Test
	void deleteById() {
		RoleEntity role=roleServ.deleteRoleById(103);
        assertEquals("HTML",role.getName());
	}
	
	@Test
	void getAllCourses() {
		List<RoleEntity> roleList=roleServ.getAllRoles();
		assertEquals(3,roleList.size());
	}


}
