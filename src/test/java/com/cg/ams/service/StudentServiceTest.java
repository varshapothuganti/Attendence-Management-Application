package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.dto.CourseInputDTO;
import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.RoleEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	IStudentService studentService;
	
	@Autowired
	UserServiceImpl userServ;
	
	@Test
	@Disabled
	public void addTest() throws Exception {
		RoleEntity role1 = new RoleEntity(3, "student", "Student can view ");
		UserInputDTO userInDTO = new UserInputDTO(110,"Jon","JonLastname","IamJon",
				"12345678901","12345678901",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9876543210","male","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		SubjectDTO subDTO3 = new SubjectDTO(503,"Angular","A4503","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		subList.add(subDTO3);
		StudentInputDTO student = new StudentInputDTO(110, 10,"Jon@gmail.com",userInDTO, subList, "Jonfather@gmail.com", "9487654321");
		long id = studentService.add(student);
		StudentOutputDTO stdOutDTO = studentService.findByPk(id);
		assertEquals("Jon",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
		assertThatExceptionOfType(DuplicateRecordException.class).isThrownBy(()->{studentService.add(student);});
	}

	@Test
	public void findByPkTest() throws Exception {
		StudentOutputDTO stdOutDTO = studentService.findByPk(110);
		assertEquals("Jon",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
		assertThrows(RecordNotFoundException.class,() -> {studentService.findByPk(20);});
	}
	@Test
	void findByName()
	{
		List<StudentOutputDTO> students = studentService.findByName("Tom");
		assertEquals(2, students.size());
	}
	  @Test
	  void searchTest() throws Exception {
	   List<StudentOutputDTO> students=studentService.search("om");
	   assertEquals(3,students.size());
	  }
	@Test
	public void updateTest() throws Exception {
		RoleEntity role1 = new RoleEntity(3, "Student", "Student Can view");
		UserInputDTO userInDTO = new UserInputDTO(103,"Finny","P","IamFinny",
				"1234567890","1234567890",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"9090909090","Female","Pic1",role1);
		CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
		SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO student = new StudentInputDTO(103, 3,"finny@gmail.com",userInDTO, subList, "finfather@gmail.com", "9876543210");
		studentService.update(student);
		StudentOutputDTO stdOutDTO = studentService.findByPk(103);
		assertEquals("Finny",stdOutDTO.getFirstName());
		assertEquals("9090909090",stdOutDTO.getMobileNo());
	}
	  @Test
	  void searchPageTest() {
		    List<StudentOutputDTO> students = studentService.search("To",0, 3);
			assertEquals(3,students.size());
	  }
		@Test
		@Disabled
		public void deleteTest() throws Exception {
			RoleEntity role1 = new RoleEntity(1, "Student", "Student Can View");
			UserInputDTO userInDTO = new UserInputDTO(242,"Varsha","P","VarshaP",
					"123456","123456",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
					"9876543210","Female","Pic1",role1);
			CourseInputDTO c1 = new CourseInputDTO(500,"CSE","Computer Science Engineering");
			SubjectDTO subDTO1 = new SubjectDTO(501,"AI","A4501","First",c1);
			SubjectDTO subDTO2 = new SubjectDTO(502,"ML","A4502","First",c1);
			List<SubjectDTO> subList = new ArrayList<>();
			subList.add(subDTO1);
			subList.add(subDTO2);
			StudentInputDTO student = new StudentInputDTO(212, 42,"varsha@gmail.com",userInDTO, subList, "father@gmail.com", "9087654321");
			studentService.delete(student);
			assertThrows(RecordNotFoundException.class,() -> {studentService.delete(student);});
		}
	
	
	
	
	



}
