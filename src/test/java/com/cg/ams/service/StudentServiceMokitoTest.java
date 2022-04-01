package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;





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

import com.cg.ams.dto.CourseInputDTO;
import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.dto.SubjectDTO;
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
		CourseInputDTO c1 = new CourseInputDTO(501,"CSE","Computer Science Engineering");
		CourseInputDTO c2 = new CourseInputDTO(102,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		studentService.add(stdInDTO);
		Mockito.when(studentRepository.findById(1101)).thenReturn(Optional.of(student));
		StudentOutputDTO stdOutDTO = studentService.findByPk(1101);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
	}
	
	@Test
	public void findByPkTest() throws Exception {
		CourseInputDTO c1 = new CourseInputDTO(501,"CSE","Computer Science Engineering");
		CourseInputDTO c2 = new CourseInputDTO(102,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.findById(1101)).thenReturn(Optional.of(student));
		StudentOutputDTO stdOutDTO = studentService.findByPk(1101);
		assertEquals("Varsha",stdOutDTO.getFirstName());
		assertEquals("9876543210",stdOutDTO.getMobileNo());
	}
	@Test
	public void updateTest() throws Exception {
		CourseInputDTO c1 = new CourseInputDTO(501,"CSE","Computer Science Engineering");
		CourseInputDTO c2 = new CourseInputDTO(102,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Mockito.when(studentRepository.findById(1101)).thenReturn(Optional.of(student));
		studentService.update(stdInDTO);
		assertEquals("Varsha",studentService.findByPk(1101).getFirstName());
	}
	@Test
	public void deleteTest() throws Exception {
		CourseInputDTO c1 = new CourseInputDTO(501,"CSE","Computer Science Engineering");
		CourseInputDTO c2 = new CourseInputDTO(102,"CSE","Computer Science Engineering");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		StudentInputDTO stdInDTO = new StudentInputDTO(1101,1,"Varsha","9876543210","pic1.jpg",subList);
		StudentEntity student = new StudentEntity(stdInDTO);
		Mockito.when(studentRepository.findById(1101)).thenReturn(Optional.of(student));
		studentService.delete(stdInDTO);
		Mockito.verify(studentRepository).deleteById(student.getId());
	}


	
	

}
