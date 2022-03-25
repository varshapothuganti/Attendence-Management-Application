package com.cg.ams.controller;

import com.cg.ams.entity.CourseEntity;
import com.cg.ams.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    ICourseService courseServ;

    // Add Course
    @PostMapping("/addCourse/")
    ResponseEntity<String> add(@RequestBody CourseEntity course) {
        String response = courseServ.add(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Course
    @PutMapping("/updateCourse")
    ResponseEntity<CourseEntity> update(@RequestBody CourseEntity course) {
        CourseEntity updatedCourse = courseServ.update(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK); // 200 Ok
    }

    // Update Course Name
    @PutMapping("/updateNameById/{id}")
    ResponseEntity<CourseEntity> updateNameById(@PathVariable("id") long id, @RequestBody String name) {
        CourseEntity updatedCourse = courseServ.updateNameById(id, name);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK); // 200 Ok
    }

    // Delete Course
    @DeleteMapping("/deleteCourse")
    ResponseEntity<CourseEntity> delete(@RequestBody CourseEntity course) {
        CourseEntity deletedCourse = courseServ.delete(course);
        return new ResponseEntity<>(deletedCourse, HttpStatus.OK); // 200 Ok
    }

    // Get Course By Name
    @GetMapping("/courseByName/{name}")
    ResponseEntity<CourseEntity> findByName(@PathVariable("name") String name) {
        CourseEntity course = courseServ.findByName(name);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // Get Course By Primary Key
    @GetMapping("/courseById/{id}")
    ResponseEntity<CourseEntity> findById(@PathVariable("id") long id) {
        CourseEntity course = courseServ.findById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // Delete Course By Id
    @DeleteMapping("/courseById/{id}")
    ResponseEntity<CourseEntity> deleteById(@PathVariable("id") long id) {
        CourseEntity deletedCourse = courseServ.deleteById(id);
        return new ResponseEntity<>(deletedCourse, HttpStatus.OK); // 200 Ok
    }

    // Delete Course By Name
    @DeleteMapping("/courseByName/{name}")
    ResponseEntity<CourseEntity> deleteByName(@PathVariable("name") String name) {
        CourseEntity deletedCourse = courseServ.deleteByName(name);
        return new ResponseEntity<>(deletedCourse, HttpStatus.OK); // 200 Ok
    }

    // Get All Courses
    @GetMapping("/courses")
    ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> course = courseServ.getAllCourses();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
