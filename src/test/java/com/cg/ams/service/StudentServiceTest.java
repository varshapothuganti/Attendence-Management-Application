package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


import static org.junit.jupiter.api.Assertions.*;


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
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	IStudentService studentService;
	

	@Test
	@Disabled
	public void addTest() throws DuplicateRecordException {
		CourseInputDTO c1 = new CourseInputDTO(501,"CSE","Computer Science Engineering");
		CourseInputDTO c2 = new CourseInputDTO(102,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO student = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		long id = studentService.add(student);
		StudentOutputDTO stdOutDTO = studentService.findByPk(id);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
		assertThatExceptionOfType(DuplicateRecordException.class).isThrownBy(()->{studentService.add(student);});
	}

	

	
	@Test
	public void findByPkTest() throws Exception {
		StudentOutputDTO stdOutDTO = studentService.findByPk(1101);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
		assertThrows(RecordNotFoundException.class,() -> {studentService.findByPk(200);});
	}
	@Test
	void findByName()
	{
		List<StudentOutputDTO> students = studentService.findByName("Raj");
		assertEquals(2, students.size());
	}
	  @Test
	  void searchTest() throws Exception {
	   List<StudentOutputDTO> students=studentService.search("Ray");
	   assertEquals(2,students.size());
	  }
	@Test
	public void updateTest() {
		CourseInputDTO c1 = new CourseInputDTO(101,"name1","description1");
		CourseInputDTO c2 = new CourseInputDTO(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdDTO = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		StudentInputDTO stdDTO1 = new StudentInputDTO(1111,1,"Nemo","9999999990","pic1.jpg",subList);
		studentService.update(stdDTO);
		StudentOutputDTO stdOutDTO = studentService.findByPk(1101);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
		assertThrows(RecordNotFoundException.class,() -> {studentService.update(stdDTO1);});
	}
	  @Test
	  void searchPageTest() {
		    List<StudentOutputDTO> students = studentService.search("Ra",0, 3);
			assertEquals(3,students.size());
	  }
		@Test
		@Disabled
		public void deleteTest() throws Exception {
			CourseInputDTO c1 = new CourseInputDTO(101,"name1","description1");
			CourseInputDTO c2 = new CourseInputDTO(102,"name2","description2");
			SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
			SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
			List<SubjectDTO> subList = new ArrayList<>();
			subList.add(subDTO1);
			subList.add(subDTO2);
			StudentInputDTO afInDTO = new StudentInputDTO(1002,1,"Varsha","9876543210","pic1.jpg",subList);
			studentService.delete(afInDTO);
			assertThrows(RecordNotFoundException.class,() -> {studentService.delete(afInDTO);});
		}
	
	
	
	
	



}
