package com.cg.ams.service;

import com.cg.ams.entity.RoleEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RoleNotFoundException;
import com.cg.ams.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleRepository roleRepo;

    // Add Role Entity
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

    // Delete Role Entity
    @Override
    public RoleEntity deleteRole(RoleEntity role) {
    	Optional<RoleEntity> opt=roleRepo.findById(role.getId());
		if(!opt.isPresent()) {
			throw new RoleNotFoundException("Could not find Role with Id:"+role.getId());
		}
		else {
			roleRepo.delete(role);
			return role;
		}
    }

    // Update Role Entity
    @Override
    public RoleEntity updateRole(RoleEntity role) {
    	Optional<RoleEntity> opt=roleRepo.findById(role.getId());
		if(!opt.isPresent()) {
			throw new RoleNotFoundException("Could not find Role with Id:"+role.getId());
		}
		else {
			roleRepo.save(role);
			return role;
		}
    }

    // List All Roles
    @Override
    public List<RoleEntity> getAllRoles() {
         return roleRepo.findAll();
 
    }

    // Delete Role By Id
    @Override
    public RoleEntity deleteRoleById(long id) {
        Optional<RoleEntity> opt = roleRepo.findById(id);
        if (!opt.isPresent()) {
            throw new RoleNotFoundException("Could not fing Role with Id:" + id);
        }
        roleRepo.deleteById(id);
        return opt.get();
    }

    // Delete Role By Name
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

    // Update Role Name By Id
    @Override
    public RoleEntity updateRoleNameById(long id, String name) {
        Optional<RoleEntity> opt = roleRepo.findById(id);
        if (opt != null) {
            RoleEntity role = opt.get();
            role.setName(name);
            roleRepo.save(role);
            return role;
        } else {
            throw new RoleNotFoundException("Could not find Role with Id:" + id);
        }
    }

    // Get Role By Id
    @Override
    public RoleEntity getRoleById(long id) {
        Optional<RoleEntity> role = roleRepo.findById(id);
        if (!role.isPresent()) {
            throw new RoleNotFoundException("Could not find Role with Id:" + id);
        } else {
            return role.get();
        }
    }

    // Get Role By Name
    @Override
    public RoleEntity getRoleByName(String name) {
        RoleEntity role = roleRepo.findByName(name);
        if (role == null) {
            throw new RoleNotFoundException("Could not find Role with name:" + name);
        } else {
            return role;
        }
    }
    
    @Override
	public Page<RoleEntity> getAllRolesWithPagination(int offset, int pageSize) {
		Page<RoleEntity> roles = roleRepo.findAll(PageRequest.of(offset, pageSize));
		return roles;
	}

}
