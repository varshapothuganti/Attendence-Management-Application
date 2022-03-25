package com.cg.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ams.entity.AssignFacultyEntity;

@Repository
public interface AssignFacultyRepository extends JpaRepository<AssignFacultyEntity,Long>{
	
	public AssignFacultyEntity findByUserName(String name);
	
}
