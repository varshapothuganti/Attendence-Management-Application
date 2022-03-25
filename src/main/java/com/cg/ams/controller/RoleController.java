package com.cg.ams.controller;

import com.cg.ams.entity.RoleEntity;
import com.cg.ams.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    IRoleService roleServ;

    // Add Role
    @PostMapping("/addRole/")
    ResponseEntity<String> addRole(@RequestBody RoleEntity role) {
        String response = roleServ.addRole(role);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Role
    @PutMapping("/updateRole")
    ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity role) {
        RoleEntity updatedRole = roleServ.updateRole(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    // Update Role Name By Id
    @PutMapping("/updateRoleNameById/{id}")
    ResponseEntity<RoleEntity> updateRoleNameById(@PathVariable("id") long id, @RequestBody String name) {
        RoleEntity updatedRole = roleServ.updateRoleNameById(id, name);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    // Delete Role
    @DeleteMapping("/deleteRole")
    ResponseEntity<RoleEntity> deleteRole(@RequestBody RoleEntity role) {
        RoleEntity deletedRole = roleServ.deleteRole(role);
        return new ResponseEntity<>(deletedRole, HttpStatus.OK);
    }

    // Delete Role By Id
    @DeleteMapping("/roleById/{id}")
    ResponseEntity<RoleEntity> deleteRoleById(@PathVariable("id") long id) {
        RoleEntity deletedRole = roleServ.deleteRoleById(id);
        return new ResponseEntity<>(deletedRole, HttpStatus.OK); // 200 Ok
    }

    // Delete Role By Name
    @DeleteMapping("/roleByName/{name}")
    ResponseEntity<RoleEntity> deleteByName(@PathVariable("name") String name) {
        RoleEntity deletedRole = roleServ.deleteRoleByName(name);
        return new ResponseEntity<>(deletedRole, HttpStatus.OK); // 200 Ok
    }

    // Get All Roles
    @GetMapping("/roles")
    ResponseEntity<List<RoleEntity>> getAllRoles() {
        List<RoleEntity> roles = roleServ.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Get Role By Name
    @GetMapping("/roleByName/{name}")
    ResponseEntity<RoleEntity> getRoleByName(@PathVariable("name") String name) {
        RoleEntity role = roleServ.getRoleByName(name);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Get Course By Id
    @GetMapping("/roleById/{id}")
    ResponseEntity<RoleEntity> getRoleById(@PathVariable("id") long id) {
        RoleEntity role = roleServ.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

}
