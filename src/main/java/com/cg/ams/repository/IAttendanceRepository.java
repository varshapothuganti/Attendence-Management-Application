package com.cg.ams.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ams.entity.Attendance;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query(value = "select attendance.* from attendance where attendance.student_name=:student_name", nativeQuery = true)
	Attendance findByAttName(@Param("student_name") String student_name);

	List<Attendance> findByStudentNameContainingAllIgnoreCase(String name, Pageable currentPage);

}
