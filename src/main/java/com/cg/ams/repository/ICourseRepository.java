package com.cg.ams.repository;

import com.cg.ams.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {

    CourseEntity findByName(String name);

}
