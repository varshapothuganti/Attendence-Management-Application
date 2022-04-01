package com.cg.ams.repository;


import java.util.List;




import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ams.entity.StudentEntity;

/**
 * Responsible for operating on Database. Performs the basic CRUD operations among others.
 * This interface extends JpaRepository interface
 *
 * @author Varsha
 */

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Long> {

    
	public Optional<StudentEntity> findById(long id);
	
	@Query(value = "select student.* FROM student where first_name=:name or last_name=:name", nativeQuery = true)
    Optional<List<StudentEntity>> findByName(@Param("name") String name);

   public List<StudentEntity> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstName, String lastName, Pageable pageable);

   @Query(value="select student.* from student where first_name ilike %:first_name% or last_name ilike %:last_name%",nativeQuery=true)
   public List<StudentEntity> findStudentByFirstNameOrLastName(@Param("first_name") String firstName, @Param("last_name") String lastName);

	public boolean existsByRollNo(long rollNo);


}
