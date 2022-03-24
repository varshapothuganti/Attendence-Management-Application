package com.example.sprint1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprint1.bean.CourseEntity;
import com.example.sprint1.exception.CourseNotFoundException;
import com.example.sprint1.exception.DuplicateRecordException;
import com.example.sprint1.repository.ICourseRepository;

@Service
public class CourseServiceImpl implements ICourseService {
	
	@Autowired
	ICourseRepository courseRepo;

	@Override
	public String add(CourseEntity course) {
		Optional<CourseEntity> opt=courseRepo.findById(course.getId());
		if(opt.isPresent()) {
			throw new DuplicateRecordException("Duplicate Record Entered with id->"+course.getId());
		}
		else {
		courseRepo.save(course);
		return "Course Added Successfully";
		}
	}

	@Override
	public CourseEntity update(CourseEntity course) {
		CourseEntity course1=courseRepo.getById(course.getId());
		if(course1==null) {
			throw new CourseNotFoundException("Could not find the Course with id->"+course.getId());
		}
		else {
			courseRepo.save(course);
			return course;
		}
		
		
	}

	@Override
	public CourseEntity delete(CourseEntity course) {
		CourseEntity course1 = courseRepo.getById(course.getId());
		
		if(course1!=null) {
			courseRepo.delete(course);
			return course1;
		}
		else {
			throw new CourseNotFoundException("Could not find the Course with id->"+course.getId());
		}
		
	}
	
	

	@Override
	public CourseEntity findByName(String name) {
		CourseEntity course=courseRepo.findByName(name);
		if(course!=null) {
			return course;
		}
		else {
			throw new CourseNotFoundException("Could not find the Course with name->"+name);
		}
	}

	@Override
	public CourseEntity findById(long id) {
		
		Optional<CourseEntity> opt=courseRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CourseNotFoundException("Could not find the Course with id->"+id);
		}
		else {
		return opt.get();
	}
	}

	@Override
	public CourseEntity deleteById(long id) {
		Optional<CourseEntity> opt=courseRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CourseNotFoundException("Could not find the Course with id->"+id);
		}
		courseRepo.deleteById(id);
		return opt.get();
	}

	@Override
	public CourseEntity deleteByName(String name) {
		CourseEntity course=courseRepo.findByName(name);
		if(course!=null) {
			courseRepo.delete(course);
			return course;
		}
		else {
			throw new CourseNotFoundException("Could not find the Course with name->"+name);
		}
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		List<CourseEntity> courseList=courseRepo.findAll();
		return courseList;
	}

	@Override
	public CourseEntity updateNameById(long id,String name) {
		Optional<CourseEntity> opt=courseRepo.findById(id);
		if(!opt.isPresent()) {
			throw new CourseNotFoundException("Could not find the Course with id->"+id);
		}
		CourseEntity course=opt.get();
		course.setName(name);
		courseRepo.save(course);
		return course;
		
	}

}
