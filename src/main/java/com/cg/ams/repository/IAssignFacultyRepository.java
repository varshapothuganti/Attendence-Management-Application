package com.cg.ams.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ams.entity.AssignFacultyEntity;

/*
 * Interface which handles all the manipulations on the database with JPA queries
 * @Author Ramu
 */

@Repository
public interface IAssignFacultyRepository extends JpaRepository<AssignFacultyEntity,Long>{
	
	public AssignFacultyEntity findByUserName(String name);
	public List<AssignFacultyEntity> findByUserNameIgnoreCase(String name,Pageable pageable);
	public List<AssignFacultyEntity> findByUserNameIgnoreCase(String name);
	
}
