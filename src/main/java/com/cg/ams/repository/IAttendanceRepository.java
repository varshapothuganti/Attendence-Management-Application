package com.cg.ams.repository;

import com.cg.ams.bean.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
<<<<<<<< HEAD:src/main/java/com/cg/ams/repository/AttendenceRepository.java
public interface AttendenceRepository extends JpaRepository<Attendance, Long> {
	
	@Query(value="select attendance.* from attendance where attendance.student_name=:student_name", nativeQuery=true)
	Attendance findByAttName(@Param("student_name") String student_name);
========
public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "select attendance.* from attendance where attendance.student_name=:student_name", nativeQuery = true)
    Attendance findByAttName(@Param("student_name") String student_name);
>>>>>>>> 93aa278 (Add routes for 'forgot-password', 'register', 'change-password', 'authenticate'.):src/main/java/com/cg/ams/repository/IAttendanceRepository.java

}
