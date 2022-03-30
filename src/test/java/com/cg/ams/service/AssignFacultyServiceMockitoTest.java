package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

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

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.entity.CourseEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.IAssignFacultyRepository;

@ExtendWith(SpringExtension.class)
public class AssignFacultyServiceMockitoTest {
	
	@InjectMocks
	AssignFacultyServiceImpl afServ;
	
	@MockBean
	IAssignFacultyRepository afRep;
	
	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	//@Disabled
	public void addTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		long l = afServ.add(afInDTO);
		Mockito.verify(afRep,times(1)).save(afe);
		assertEquals(1111,l);
	}
	
	@Test
	//@Disabled
	public void findByPkTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(1111);
		assertEquals("firstName1lastName1",afOutDTO.getUsername());
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(2);});
	}
	
	@Test
	//@Disabled
	public void findByNameTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findByUserName("firstName1lastName1")).thenReturn(afe);
		AssignFacultyOutputDTO afOutDTO = afServ.findByName("firstName1lastName1");
		assertEquals(1111,afOutDTO.getId());
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByName("abc");});
	}
	
	@Test
	//@Disabled
	public void updateTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		afServ.update(afInDTO);
		assertEquals("f-Name1l-Name1",afServ.findByPk(1111).getUsername());
	}
	
	@Test
	//@Disabled
	public void deleteTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		afServ.delete(afInDTO);
		Mockito.verify(afRep).deleteById(afe.getId());
	}
	
	@Test
	//@Disabled
	public void searchByPagesTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> afList = new ArrayList<>();
		afList.add(afe);
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName(), PageRequest.of(0, 1))).thenReturn(afList);
		List<AssignFacultyOutputDTO> al = afServ.search(afInDTO, 0, 1);
		List<AssignFacultyOutputDTO> al1 = afServ.search(afInDTO, 1, 1);
		assertEquals(1,al.size());
		assertEquals(0,al1.size());
	}
	
	@Test
	//@Disabled
	public void searchTest() throws Exception {
		UserInputDTO userInDTO = new UserInputDTO(1L,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1","profilePic1",11L);
		CourseEntity c1 = new CourseEntity(101,"name1","description1");
		CourseEntity c2 = new CourseEntity(102,"name2","description2");
		SubjectDTO subDTO1 = new SubjectDTO(111,"subjectName1","code1","semester1",c1);
		SubjectDTO subDTO2 = new SubjectDTO(112,"subjectName2","code2","semester2",c2);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111,userInDTO,subList,"class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> afList = new ArrayList<>();
		afList.add(afe);
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName())).thenReturn(afList);
		List<AssignFacultyOutputDTO> al = afServ.search(afInDTO);
		assertEquals(1,al.size());
	}
	
}