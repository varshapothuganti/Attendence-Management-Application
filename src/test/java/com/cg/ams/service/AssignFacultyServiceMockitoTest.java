package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.AssignFacultyRepository;

@ExtendWith(SpringExtension.class)
public class AssignFacultyServiceMockitoTest {
	
	@InjectMocks
	AssignFacultyServiceImpl afServ;
	
	@MockBean
	AssignFacultyRepository afRep;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	//@Disabled
	public void findByPkTest() throws Exception {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
		AssignFacultyEntity afe1 = afServ.findByPk(1);
		assertEquals(afe,afe1);
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(2);});
	}
	
	@Test
	//@Disabled
	public void findByNameTest() throws Exception {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.findByUserName("Username1")).thenReturn(afe);
		AssignFacultyEntity afe1 = afServ.findByName("Username1");
		assertEquals(afe,afe1);
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByName("username1");});
	}
	
	@Test
	//@Disabled
	public void addTest() throws Exception {
		//AssignFacultyEntity afe = new AssignFacultyEntity(3,13,"Username3",103,"Coursename3",1003,"Subject3","Semester3","Class3");
		AssignFacultyEntity afe=new AssignFacultyEntity(3,13,"Username3","Class3");
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		long l = afServ.add(afe);
		Mockito.verify(afRep,times(1)).save(afe);
		assertEquals(3,l);
	}
	
	@Test
	//@Disabled
	public void updateTest() throws Exception {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
		afServ.update(afe);
		assertEquals(afe,afServ.findByPk(1));
	}
	
	@Test
	@Disabled
	public void deleteTest() throws Exception {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.findById(1L)).thenReturn(Optional.of(afe));
		afServ.delete(afe);
		Mockito.verify(afRep).deleteById(afe.getId());
		Mockito.when(afRep.findById(1L)).thenReturn(Optional.empty());
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(1L);});
	}
	
	@Test
	//@Disabled
	public void searchByPagesTest() {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName(), PageRequest.of(0, 1))).thenReturn(List.of(afe));
		List<AssignFacultyEntity> al = afServ.search(afe, 0, 1);
		assertEquals(1,al.size());
	}
	
	@Test
	//@Disabled
	public void searchTest() {
		//AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe=new AssignFacultyEntity(1,11,"Username1","Class1");
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName())).thenReturn(List.of(afe));
		List<AssignFacultyEntity> al = afServ.search(afe);
		assertEquals(1,al.size());
	}
	
}