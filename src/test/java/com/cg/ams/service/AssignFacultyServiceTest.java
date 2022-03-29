package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.CourseEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
public class AssignFacultyServiceTest {
	
	@Autowired
	IAssignFacultyService afServ;
	
	@Test
	//@Disabled
	public void addTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,List.of(subDTO1,subDTO2),"class1");
		long id = afServ.add(afInDTO);
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(id);
		assertEquals("class1",afOutDTO.getTotalClass());
		assertEquals("firstName1lastName1",afOutDTO.getUsername());
		assertThrows(DuplicateRecordException.class,() -> {afServ.add(afInDTO);});
	}
	
	@Test
	//@Disabled
	public void findByPkTest() throws Exception {
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(1111);
		assertEquals("class_1",afOutDTO.getTotalClass());
		assertEquals("f-Name1l-Name1",afOutDTO.getUsername());
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(200);});
	}
	
	@Test
	//@Disabled
	public void findByNameTest() throws Exception {
		AssignFacultyOutputDTO afOutDTO = afServ.findByName("firstName1lastName1");
		assertEquals("class1",afOutDTO.getTotalClass());
		assertEquals("firstName1lastName1",afOutDTO.getUsername());
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByName("abc");});
	}
	
	@Test
	//@Disabled
	public void updateTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1,"f-Name1","l-Name1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,List.of(subDTO1,subDTO2),"class_1");
		AssignFacultyInputDTO afInDTO1 = new AssignFacultyInputDTO(1112,userInDTO,List.of(subDTO1,subDTO2),"class_2");
		afServ.update(afInDTO);
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(1111);
		assertEquals("class_1",afOutDTO.getTotalClass());
		assertEquals("f-Name1l-Name1",afOutDTO.getUsername());
		assertThrows(RecordNotFoundException.class,() -> {afServ.update(afInDTO1);});
	}
	
	@Test
	//@Disabled
	public void searchByPagesTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,List.of(subDTO1,subDTO2),"class1");
		List<AssignFacultyOutputDTO> al1 = afServ.search(afInDTO, 0, 2);
		List<AssignFacultyOutputDTO> al2 = afServ.search(afInDTO, 1, 1);
		assertEquals(1,al1.size());
		assertEquals(0,al2.size());
	}
	
	@Test
	//@Disabled
	public void searchTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,List.of(subDTO1,subDTO2),"class1");
		List<AssignFacultyOutputDTO> al = afServ.search(afInDTO);
		assertEquals(1,al.size());
	}
	
	@Test
	//@Disabled
	public void deleteTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,List.of(subDTO1,subDTO2),"class1");
		afServ.delete(afInDTO);
		assertThrows(RecordNotFoundException.class,() -> {afServ.delete(afInDTO);});
	}
	
}