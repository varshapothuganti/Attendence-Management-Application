package com.example.sprint1.service;

import java.util.List;

import com.example.sprint1.bean.CourseEntity;

public interface ICourseService {
	
	public String add(CourseEntity course);
	public CourseEntity update(CourseEntity course);
	public CourseEntity updateNameById(long id,String name);
	public CourseEntity delete(CourseEntity course);
	public CourseEntity deleteById(long id);
	public CourseEntity deleteByName(String name);
	public CourseEntity findByName(String name);
	public CourseEntity findById(long id);
	public List<CourseEntity> getAllCourses();

}
