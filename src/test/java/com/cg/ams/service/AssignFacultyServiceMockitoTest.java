package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.entity.CourseEntity;
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
		UserInputDTO userInDTO = new UserInputDTO(1,"firstName1","lastName1","login1",
				"password1","password1",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),
				"mobileNo1","gender1",11,"profilePic1");
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
	
//	@Test
//	//@Disabled
//	public void findByPkTest() throws Exception {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
//		AssignFacultyEntity afe1 = afServ.findByPk(1);
//		assertEquals(afe,afe1);
//		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(2);});
//	}
//	
//	@Test
//	//@Disabled
//	public void findByNameTest() throws Exception {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.findByUserName("Username1")).thenReturn(afe);
//		AssignFacultyEntity afe1 = afServ.findByName("Username1");
//		assertEquals(afe,afe1);
//		assertThrows(RecordNotFoundException.class,() -> {afServ.findByName("username1");});
//	}
//	
//	@Test
//	//@Disabled
//	public void updateTest() throws Exception {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.save(afe)).thenReturn(afe);
//		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
//		afServ.update(afe);
//		assertEquals(afe,afServ.findByPk(1));
//	}
//	
//	@Test
//	@Disabled
//	public void deleteTest() throws Exception {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
//		afServ.delete(afe);
//		Mockito.verify(afRep).deleteById(afe.getId());
//		Mockito.when(afRep.findById(1L)).thenReturn(Optional.empty());
//		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(1L);});
//	}
//	
//	@Test
//	//@Disabled
//	public void searchByPagesTest() {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName(), PageRequest.of(0, 1))).thenReturn(List.of(afe));
//		List<AssignFacultyEntity> al = afServ.search(afe, 0, 1);
//		assertEquals(1,al.size());
//	}
//	
//	@Test
//	//@Disabled
//	public void searchTest() {
//		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
//		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
//		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName())).thenReturn(List.of(afe));
//		List<AssignFacultyEntity> al = afServ.search(afe);
//		assertEquals(1,al.size());
//	}
//	
}