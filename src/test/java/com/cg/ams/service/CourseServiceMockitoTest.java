package com.cg.ams.service;

import com.cg.ams.dto.CourseOutputDTO;
import com.cg.ams.entity.CourseEntity;
import com.cg.ams.repository.ICourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CourseServiceMockitoTest {

	@InjectMocks
	CourseServiceImpl courseServ;

	@MockBean
	ICourseRepository courseRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	// Update Course Entity using Mockito
	@Test
	void updateCourse() {
		CourseEntity course = new CourseEntity(5, "JAVA", "JAVA ia a Programming Language");
		Mockito.when(courseRepo.findById(course.getId())).thenReturn(Optional.of(course));
		Mockito.when(courseRepo.save(course)).thenReturn(course);
		CourseEntity result = courseServ.update(course);
		assertEquals(5, result.getId());
		assertEquals("JAVA", result.getName());
		assertEquals("JAVA ia a Programming Language", result.getDescription());
	}

	// Delete Course Entity using Mockito
	@Test
	void deleteCourse() {
		CourseEntity course = new CourseEntity(5, "JAVA", "JAVA ia a Programming Language");
		Mockito.when(courseRepo.findById(course.getId())).thenReturn(Optional.of(course));
//		Mockito.when(courseRepo.delete(course)).thenReturn(Mockito.doNothing());
		CourseEntity result = courseServ.delete(course);
		assertEquals(5, result.getId());
		assertEquals("JAVA", result.getName());
		assertEquals("JAVA ia a Programming Language", result.getDescription());
	}

	// Add Course entity using Mockito
	@Test
	void addCourse() {
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		Mockito.when(courseRepo.save(course)).thenReturn(course);
		String s = courseServ.add(course);
		assertEquals("Course Added Successfully", s);
	}

	// Get Course by Name
	@Test
	void findByName() {
		String s = new String("Java");
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		Mockito.when(courseRepo.findByName(s)).thenReturn(course);
		CourseEntity course1 = courseServ.findByName(s);
		assertEquals(5, course1.getId());
		assertEquals("Java", course1.getName());
		assertEquals("Java is a Programming Language", course1.getDescription());
	}

	// Get Course By Id
	@Test
	void findById() {
		long input = 5;
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		Mockito.when(courseRepo.findById(input)).thenReturn(Optional.of(course));
		CourseEntity course1 = courseServ.findById(input);
		assertEquals(5, course1.getId());
		assertEquals("Java", course1.getName());
		assertEquals("Java is a Programming Language", course1.getDescription());
	}

	// Delete Course By Id
	@Test
	void deleteById() {
		long input = 5;
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		Mockito.when(courseRepo.findById(input)).thenReturn(Optional.of(course));
		CourseEntity course1 = courseServ.deleteById(input);
		assertEquals(5, course1.getId());
		assertEquals("Java", course1.getName());
		assertEquals("Java is a Programming Language", course1.getDescription());
	}

	// Delete Course By Name
	@Test
	void deleteByName() {
		String s = new String("Java");
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		Mockito.when(courseRepo.findByName(s)).thenReturn(course);
		CourseEntity course1 = courseServ.deleteByName(s);
		assertEquals(5, course1.getId());
		assertEquals("Java", course1.getName());
		assertEquals("Java is a Programming Language", course1.getDescription());
	}

	// Update Course Name By Id
	@Test
	void updateNameById() {
		long id = 5;
		String name = "Python";
		CourseEntity course = new CourseEntity(5, "Java", "Python is a Programming Language");
		Mockito.when(courseRepo.findById(id)).thenReturn(Optional.of(course));
		CourseEntity course1 = courseServ.updateNameById(id, name);
		assertEquals(5, course1.getId());
		assertEquals("Python", course1.getName());
		assertEquals("Python is a Programming Language", course1.getDescription());
	}

	// List All Courses
	@Test
	void getAllCourses() {
		List<CourseEntity> clist = new ArrayList<>();
		CourseEntity course1 = new CourseEntity(5, "Java", "Java is a Programming Language");
		CourseEntity course2 = new CourseEntity(6, "Python", "Python is a Programming Language");
		CourseEntity course3 = new CourseEntity(7, "C++", "C++ is a Programming Language");
		clist.add(course1);
		clist.add(course2);
		clist.add(course3);
		Mockito.when(courseRepo.findAll()).thenReturn(clist);
		List<CourseEntity> courses = courseServ.getAllCourses();
		assertEquals(3, courses.size());
	}

	// Add Course using DTO
	@Test
	void addDto() {
		CourseEntity course = new CourseEntity(5, "Java", "Java is a Programming Language");
		// Mockito.when(courseRepo.findById(course.getId())).thenReturn(Optional.of(null));
		Mockito.when(courseRepo.save(course)).thenReturn(course);
		CourseOutputDTO res = courseServ.addDto(course);
		assertEquals("Java", res.getName());
	}

}
