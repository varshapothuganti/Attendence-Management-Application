package com.cg.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ams.entity.StudentEntity;


@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Integer> {

	public StudentEntity findById(long id);
    @Query(value = "select student_entity.* from student_entity where student_entity.first_name=:first_name", nativeQuery = true)
    List<StudentEntity> findByFirstName(@Param("first_name") String name);
}
