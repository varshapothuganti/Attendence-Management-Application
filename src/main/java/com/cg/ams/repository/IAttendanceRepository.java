package com.cg.ams.repository;

import com.cg.ams.bean.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "select attendance.* from attendance where attendance.student_name=:student_name", nativeQuery = true)
    Attendance findByAttName(@Param("student_name") String student_name);

}
