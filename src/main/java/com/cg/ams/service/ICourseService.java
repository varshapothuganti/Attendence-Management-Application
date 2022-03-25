package com.cg.ams.service;

import com.cg.ams.entity.CourseEntity;

import java.util.List;

public interface ICourseService {

    public String add(CourseEntity course);

    public CourseEntity update(CourseEntity course);

    public CourseEntity updateNameById(long id, String name);

    public CourseEntity delete(CourseEntity course);

    public CourseEntity deleteById(long id);

    public CourseEntity deleteByName(String name);

    public CourseEntity findByName(String name);

    public CourseEntity findById(long id);

    public List<CourseEntity> getAllCourses();

}
