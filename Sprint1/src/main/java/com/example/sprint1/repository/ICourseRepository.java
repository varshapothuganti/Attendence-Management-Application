package com.example.sprint1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprint1.bean.CourseEntity;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity,Long> {
	
	CourseEntity findByName(String name);

}
