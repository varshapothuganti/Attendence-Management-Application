package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.IStudentRepository;



@ExtendWith(SpringExtension.class)
class StudentServiceMokitoTest {
	
	

	@InjectMocks
	StudentServiceImpl studentService;

	// @MockBean - Creates Mock of a class or interface

	@MockBean
	IStudentRepository studentRepository;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addTest() throws RecordNotFoundException, ParseException {
		StudentEntity student =new StudentEntity();
		student.setId(1000);
		student.setRollNo(2);
		student.setFirstName("Nemo");
		student.setLastName("Clown");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
		student.setGender("male");
		student.setMobileNo("9999999990");
		student.setCourseId(5);
		student.setCourseName("CSE");
		student.setSubjectId(501);
		student.setSubjectName("AI");
		student.setSemester("First");
		student.setEmailId("nemo@gmail.com");
		student.setFatherEmailId("Marlin@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		studentService.add(student);
		assertEquals("Nemo", student.getFirstName());
		assertEquals("nemo@gmail.com", student.getEmailId());	
	}
	
	@Test
	void findByNameTest() throws Exception {
		StudentEntity student=new StudentEntity();
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		student.setId(1000);
		student.setRollNo(2);
		student.setFirstName("Nemo");
		student.setLastName("Clown");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
		student.setGender("male");
		student.setMobileNo("9999999990");
		student.setCourseId(5);
		student.setCourseName("CSE");
		student.setSubjectId(501);
		student.setSubjectName("AI");
		student.setSemester("First");
		student.setEmailId("nemo@gmail.com");
		student.setFatherEmailId("Marlin@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		list.add(student);
		Mockito.when(studentRepository.findByName("Nemo")).thenReturn(Optional.of(list));
		List<StudentEntity> students=studentService.findByName("Nemo");
		assertEquals(1, students.size());

	   }
	 @Test
	 void findByPkTest() throws Exception {
			StudentEntity student =new StudentEntity();
			student.setId(1000);
			student.setRollNo(2);
			student.setFirstName("Nemo");
			student.setLastName("Clown");
			student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
			student.setGender("male");
			student.setMobileNo("9999999990");
			student.setCourseId(5);
			student.setCourseName("CSE");
			student.setSubjectId(501);
			student.setSubjectName("AI");
			student.setSemester("First");
			student.setEmailId("nemo@gmail.com");
			student.setFatherEmailId("Marlin@gmail.com");
			student.setFatherMobileNo("7896541230");
			student.setProfilePic("pic1.jpg");
			Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
			StudentEntity student1=studentService.findByPk(1000);
			assertEquals("Nemo", student1.getFirstName());
			assertEquals("nemo@gmail.com", student1.getEmailId());
		   
	   }
	 
	 
	@Test
	void updateTest() throws Exception{
		StudentEntity student =new StudentEntity();
		student.setId(1000);
		student.setRollNo(2);
		student.setFirstName("NemoFish");
		student.setLastName("ClownFish");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
		student.setGender("male");
		student.setMobileNo("9999999990");
		student.setCourseId(5);
		student.setCourseName("CSE");
		student.setSubjectId(501);
		student.setSubjectName("AI");
		student.setSemester("First");
		student.setEmailId("nemo@gmail.com");
		student.setFatherEmailId("Marlin@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
		studentService.update(student);
		assertEquals("NemoFish", student.getFirstName());
		assertEquals("ClownFish", student.getLastName());
	}
	@Test
	void deleteTest() throws Exception {
		StudentEntity student =new StudentEntity();
		student.setId(1000);
		student.setRollNo(2);
		student.setFirstName("NemoFish");
		student.setLastName("ClownFish");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
		student.setGender("male");
		student.setMobileNo("9999999990");
		student.setCourseId(5);
		student.setCourseName("CSE");
		student.setSubjectId(501);
		student.setSubjectName("AI");
		student.setSemester("First");
		student.setEmailId("nemo@gmail.com");
		student.setFatherEmailId("Marlin@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
		studentService.delete(student);
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.empty());
		assertThrows(RecordNotFoundException.class,()->{studentService.findByPk(1000);});
	}



}
