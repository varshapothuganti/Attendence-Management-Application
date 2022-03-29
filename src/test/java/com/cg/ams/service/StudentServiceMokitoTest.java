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
import org.springframework.data.domain.PageRequest;
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
		StudentEntity student =new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		studentService.add(student);
		assertEquals("Varsha", student.getFirstName());
		assertEquals("varsha@gmail.com", student.getEmailId());	
	}
	
	@Test
	void findByNameTest() throws Exception {
		StudentEntity student=new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findByName("Varsha")).thenReturn(Optional.of(list));
		List<StudentEntity> students=studentService.findByName("Varsha");
		assertEquals(1, students.size());

	   }
	
	 @Test
	 void findByPkTest() throws RecordNotFoundException, ParseException {
			StudentEntity student =new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
			Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
			StudentEntity student1=studentService.findByPk(1000);
			assertEquals("Varsha", student1.getFirstName());
			assertEquals("varsha@gmail.com", student1.getEmailId());  
	   }
	 
	 
	@Test
	void updateTest() throws RecordNotFoundException, ParseException{
		StudentEntity student =new StudentEntity(1000,2,"VarshaP","P",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
		studentService.update(student);
		assertEquals("VarshaP", student.getFirstName());
		assertEquals("P", student.getLastName());
	}
	@Test
	void deleteTest() throws RecordNotFoundException, ParseException {
		StudentEntity student =new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.of(student));
		studentService.delete(student);
		Mockito.when(studentRepository.findById(1000)).thenReturn(Optional.empty());
		assertThrows(RecordNotFoundException.class,()->{studentService.findByPk(1000);});
	}
	
	@Test
	void searchTest() throws RecordNotFoundException, ParseException
	{
		StudentEntity student=new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("Varsha","Varsha")).thenReturn(list);
		List<StudentEntity> students=studentService.search("Varsha");
		assertEquals(1, students.size());
		
	}
	@Test
	void searchPageTest() throws RecordNotFoundException, ParseException
	{
		StudentEntity student=new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("Varsha","Varsha", PageRequest.of(0, 1))).thenReturn(list);
		List<StudentEntity> students=studentService.search("Varsha",0,1);
		assertEquals(1, students.size());
		
	}
	
	

}
