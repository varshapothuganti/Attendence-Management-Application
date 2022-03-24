package com.example.sprint1.service;

import java.util.List;

import com.example.sprint1.bean.RoleEntity;

public interface IRoleService {
	
	String addRole(RoleEntity role);
	RoleEntity deleteRole(RoleEntity role);
	RoleEntity deleteRoleById(long id);
	RoleEntity deleteRoleByName(String name);
	RoleEntity updateRole(RoleEntity role);
	RoleEntity updateRoleNameById(long id,String name);
	List<RoleEntity> getAllRoles();
	RoleEntity getRoleById(long id);
	RoleEntity getRoleByName(String name);
}
