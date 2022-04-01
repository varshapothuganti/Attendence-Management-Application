package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;


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

import com.cg.ams.dto.CourseInputDTO;
import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.RoleEntity;
import com.cg.ams.entity.StudentEntity;
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
	public void addTest() throws Exception {
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		studentService.add(stdInDTO);
		Mockito.when(studentRepository.findById(201)).thenReturn(Optional.of(student));
		StudentOutputDTO stdOutDTO = studentService.findByPk(201);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
	}
	
	@Test
	public void findByPkTest() throws Exception {
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.findById(201)).thenReturn(Optional.of(student));
		StudentOutputDTO stdOutDTO = studentService.findByPk(201);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
	}
	@Test
	public void updateTest() throws Exception {
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Mockito.when(studentRepository.findById(201)).thenReturn(Optional.of(student));
		studentService.update(stdInDTO);
		assertEquals("Varsha",studentService.findByPk(201).getFirstName());
	}
	@Test
	public void deleteTest() throws Exception {
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.findById(201)).thenReturn(Optional.of(student));
		studentService.delete(stdInDTO);
		Mockito.verify(studentRepository).deleteById(student.getId());
	}

	@Test
	void findByNameTest() throws Exception {
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findByName("Varsha")).thenReturn(Optional.of(list));
		List<StudentOutputDTO> students=studentService.findByName("Varsha");
		assertEquals(1, students.size());

	   }

	@Test
	void searchTest() throws Exception
	{
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findStudentByFirstNameOrLastName("Varsha","Varsha")).thenReturn(list);
		List<StudentOutputDTO> students=studentService.search("Varsha");
		assertEquals(1, students.size());
		
	}

	@Test
	void searchPageTest() throws Exception
	{
		RoleEntity role1 = new RoleEntity(1, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(201,"Varsha","P","VarshaP",
				"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(201, 21,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
		StudentEntity student = new StudentEntity(stdInDTO);
		List<StudentEntity> list =new ArrayList<StudentEntity>();
		list.add(student);
		Mockito.when(studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase("Varsha","Varsha", PageRequest.of(0, 1))).thenReturn(list);
		List<StudentOutputDTO> students=studentService.search("Varsha",0,1);
		assertEquals(1, students.size());
		
	}
	
	

}
