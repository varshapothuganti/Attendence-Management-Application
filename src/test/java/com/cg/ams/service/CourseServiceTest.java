package com.cg.ams.service;

import com.cg.ams.entity.CourseEntity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Disabled
class CourseServiceTest {

    @Autowired
    ICourseService courseServ;

    // Add Course Entity
    @Test
    void addCourse() {
        CourseEntity course = new CourseEntity(93, "Python", "Python is a Programming Language");
        String s = courseServ.add(course);
        assertEquals("Course Added Successfully", s);
    }

    // Update Course Entity
    @Test
    void updateCourse() {
        CourseEntity course = new CourseEntity(104, "Cricket", "Cricket is a Sport");
        CourseEntity result = courseServ.update(course);
        assertEquals("Cricket", result.getName());
    }

    // Update Course Name by Id
    @Test
    void updateNameById() {
        CourseEntity course = courseServ.updateNameById(105, "Android");
        assertEquals("Java ia a Progtramming Language", course.getDescription());
    }

    // Delete Course Entity
    @Test
    void deleteCourse() {
        CourseEntity course = new CourseEntity(108, "Python", "Python is a Programming Language");
        CourseEntity result = courseServ.delete(course);
        assertEquals("Python", result.getName());
        assertEquals("Python ia a Programming Language", result.getDescription());

    }

    // Get Course Entity by Name
    @Test
    void getByName() {
        CourseEntity course = courseServ.findByName("CSS");
        assertEquals(101, course.getId());
    }

    // Get Course Entity By Id
    @Test
    void getById() {
        CourseEntity course = courseServ.findById(100);
        assertEquals("Html", course.getName());
    }

    // Delete Course By Name
    @Test
    void deleteByName() {
        CourseEntity course = courseServ.deleteByName("Cricket");
        assertEquals(104, course.getId());
    }

    // Delete Course By Id
    @Test
    void deleteById() {
        CourseEntity course = courseServ.deleteById(103);
        assertEquals("HTML", course.getName());
    }

    // List All Courses
    @Test
    void getAllCourses() {
        List<CourseEntity> courseList = courseServ.getAllCourses();
        assertEquals(6, courseList.size());
    }

}
