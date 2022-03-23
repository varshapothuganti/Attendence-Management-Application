package com.example.demo.com.cg.ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.com.cg.ams.entity.SubjectEntity;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
	
	public Optional<SubjectEntity> findById(long id);
	public Optional<SubjectEntity> findByName(String name);
	
	
}
