package com.cg.ams.service;

import com.cg.ams.entity.RoleEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RoleNotFoundException;
import com.cg.ams.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IRoleRepository roleRepo;

	@Override
	public String addRole(RoleEntity role) {
		Optional<RoleEntity> opt = roleRepo.findById(role.getId());
		if (opt.isPresent()) {
			throw new DuplicateRecordException("Duplicate Record Entered with id->" + role.getId());
		} else {
			roleRepo.save(role);
			return "Role Added Successfully";
		}
	}

	@Override
	public RoleEntity deleteRole(RoleEntity role) {
		Optional<RoleEntity> opt = roleRepo.findById(role.getId());
		if (!opt.isPresent()) {
			throw new RoleNotFoundException("Could not find Role with Id:" + role.getId());
		} else {
			roleRepo.delete(role);
			return role;
		}
	}

	@Override
	public RoleEntity updateRole(RoleEntity role) {
		Optional<RoleEntity> opt = roleRepo.findById(role.getId());
		if (!opt.isPresent()) {
			throw new RoleNotFoundException("Could not find Role with Id:" + role.getId());
		} else {
			roleRepo.save(role);
			return role;
		}
	}

	@Override
	public List<RoleEntity> getAllRoles() {
		return roleRepo.findAll();

	}

	@Override
	public RoleEntity deleteRoleById(long id) {
		Optional<RoleEntity> opt = roleRepo.findById(id);
		if (!opt.isPresent()) {
			throw new RoleNotFoundException("Could not fing Role with Id:" + id);
		}
		roleRepo.deleteById(id);
		return opt.get();
	}

	@Override
	public RoleEntity deleteRoleByName(String name) {
		RoleEntity role = roleRepo.findByName(name);
		if (role != null) {
			roleRepo.delete(role);
			return role;
		} else {
			throw new RoleNotFoundException("Could not find Role with name:" + name);
		}
	}

	@Override
	public RoleEntity updateRoleNameById(long id, String name) {
		Optional<RoleEntity> opt = roleRepo.findById(id);
		if (opt.isPresent()) {
			RoleEntity role = opt.get();
			role.setName(name);
			roleRepo.save(role);
			return role;
		} else {
			throw new RoleNotFoundException("Could not find Role with Id:" + id);
		}
	}

	@Override
	public RoleEntity getRoleById(long id) {
		Optional<RoleEntity> role = roleRepo.findById(id);
		if (!role.isPresent()) {
			throw new RoleNotFoundException("Could not find Role with Id:" + id);
		} else {
			return role.get();
		}
	}

	@Override
	public RoleEntity getRoleByName(String name) {
		RoleEntity role = roleRepo.findByName(name);
		if (role == null) {
			throw new RoleNotFoundException("Could not find Role with name:" + name);
		} else {
			return role;
		}
	}

}
