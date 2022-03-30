//package com.cg.ams.service;
//
//import com.cg.ams.entity.CourseEntity;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Disabled
//class CourseServiceTest {
//
//    @Autowired
//    ICourseService courseServ;
//
//    @Test
//    void addCourse() {
//        CourseEntity course = new CourseEntity(93, "Python", "Python is a Programming Language");
//        String s = courseServ.add(course);
//        assertEquals("Course Added Successfully", s);
//    }
//
//    @Test
//    void updateCourse() {
//        CourseEntity course = new CourseEntity(104, "Cricket", "Cricket is a Sport");
//        CourseEntity result = courseServ.update(course);
//        assertEquals("Cricket", result.getName());
//    }
//
//    @Test
//    void updateNameById() {
//        CourseEntity course = courseServ.updateNameById(105, "Android");
//        assertEquals("Java ia a Progtramming Language", course.getDescription());
//    }
//
//    @Test
//    void deleteCourse() {
//        CourseEntity course = new CourseEntity(108, "Python", "Python is a Programming Language");
//        CourseEntity result = courseServ.delete(course);
//        assertEquals("Python", result.getName());
//        assertEquals("Python ia a Programming Language", result.getDescription());
//
//    }
//
//    @Test
//    void getByName() {
//        CourseEntity course = courseServ.findByName("CSS");
//        assertEquals(101, course.getId());
//    }
//
//    @Test
//    void getById() {
//        CourseEntity course = courseServ.findById(100);
//        assertEquals("Html", course.getName());
//    }
//
//    @Test
//    void deleteByName() {
//        CourseEntity course = courseServ.deleteByName("Cricket");
//        assertEquals(104, course.getId());
//    }
//
//    @Test
//    void deleteById() {
//        CourseEntity course = courseServ.deleteById(103);
//        assertEquals("HTML", course.getName());
//    }
//
//    @Test
//    void getAllCourses() {
//        List<CourseEntity> courseList = courseServ.getAllCourses();
//        assertEquals(6, courseList.size());
//    }
//
//}
