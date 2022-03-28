package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
public class AssignFacultyServiceTest {
	
	@Autowired
	IAssignFacultyService afServ;
	
	@Test
	//@Disabled
	public void addTest() throws Exception {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");	
		long id = afServ.add(afe);
		AssignFacultyEntity afe1 = afServ.findByPk(id);
		assertEquals(1,id);
		assertEquals(afe,afe1);
		assertThrows(DuplicateRecordException.class,() -> {afServ.add(afe);});
	}
	
	@Test
	//@Disabled
	public void findByNameTest() throws Exception {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		AssignFacultyEntity afe1 = afServ.findByName("username1");
		assertEquals(afe,afe1);
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByName("username2");});
	}
	
	@Test
	//@Disabled
	public void updateTest() throws Exception {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		afServ.update(afe);
		AssignFacultyEntity afe1 = afServ.findByPk(1);
		AssignFacultyEntity afe2 = new AssignFacultyEntity(100,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		assertEquals(afe,afe1);
		assertThrows(RecordNotFoundException.class,() -> {afServ.update(afe2);});
	}
	
	@Test
	//@Disabled
	public void findByPkTest() throws Exception {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe1 = afServ.findByPk(1);
		assertEquals(afe,afe1);
		assertThrows(RecordNotFoundException.class,() -> {afServ.findByPk(2);});
	}
	
	@Test
	//@Disabled
	public void searchByPagesTest() {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		List<AssignFacultyEntity> al1 = afServ.search(afe, 0, 2);
		List<AssignFacultyEntity> al2 = afServ.search(afe, 1, 2);
		assertEquals(1,al1.size());
		assertEquals(0,al2.size());
	}
	
	@Test
	//@Disabled
	public void searchTest() {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		List<AssignFacultyEntity> al = afServ.search(afe);
		assertEquals(1,al.size());
	}
	
	@Test
	//@Disabled
	public void deleteTest() throws Exception {
		AssignFacultyEntity afe = new AssignFacultyEntity(1,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		afServ.delete(afe);
		AssignFacultyEntity afe1 = new AssignFacultyEntity(2,11,"username1",101,"coursename1",1001,"subject1","semester1","class1");
		assertThrows(RecordNotFoundException.class,() -> {afServ.delete(afe1);});
	}
	
}