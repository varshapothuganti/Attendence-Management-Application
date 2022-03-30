package com.cg.ams.service;

import com.cg.ams.entity.RoleEntity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Disabled
class RoleServiceTest {

    @Autowired
    IRoleService roleServ;

    // Add Role Entity
    @Test
    void addRole() {
        RoleEntity role = new RoleEntity(5, "HOD", "Head of Department");
        String s = roleServ.addRole(role);
        assertEquals("Role Added Successfully", s);
    }

    // Update Role Entity
    @Test
    void updateRole() {
        RoleEntity role = new RoleEntity(4, "PRINCIPAL", "Pricipal of Organization");
        RoleEntity result = roleServ.updateRole(role);
        assertEquals("Pricipal of Organization", result.getDescription());
    }

    // Update Role Name By Id
    @Test
    void updateNameById() {
        RoleEntity role = roleServ.updateRoleNameById(5, "Mounish");
        assertEquals("Head of Department", role.getDescription());
    }

    // Delete Role Entity
	@Test
	void deleteRole() {
		RoleEntity role=new RoleEntity(5,"Mounish","Head of Department");
		RoleEntity result=roleServ.deleteRole(role);
		assertEquals("Mounish",result.getName());
		//assertEquals("Python ia a Programming Language",result.getDescription());
		
	}

	// Get Role Entity By Name
    @Test
    void getByName() {
        RoleEntity role = roleServ.getRoleByName("USER");
        assertEquals(3, role.getId());
    }

    // Get Role Entity By Id
    @Test
    void getById() {
        RoleEntity role = roleServ.getRoleById(4);
        assertEquals("PRINCIPAL", role.getName());
    }

    // Delete Role Entity By Name
    @Test
    void deleteByName() {
        RoleEntity course = roleServ.deleteRoleByName("PRINCIPAL");
        assertEquals(4, course.getId());
    }

    // Delete Role Entity By Id
    @Test
    void deleteById() {
        RoleEntity role = roleServ.deleteRoleById(103);
        assertEquals("HTML", role.getName());
    }

    // List All Roles
    @Test
    void getAllRoles() {
        List<RoleEntity> roleList = roleServ.getAllRoles();
        assertEquals(3, roleList.size());
    }


}
