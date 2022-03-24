package com.example.sprint1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprint1.bean.RoleEntity;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
	
	RoleEntity findByName(String name);

}
