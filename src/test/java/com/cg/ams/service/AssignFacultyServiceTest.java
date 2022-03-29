package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
@Disabled
public class AssignFacultyServiceTest {

	@Autowired
	IAssignFacultyService afServ;

	@Test
	// @Disabled
	public void findByPkTest() throws Exception {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(2,12,"username2",102,"coursename2",1002,"subject2","semester2","class2");
		AssignFacultyEntity afe = new AssignFacultyEntity(2, 12, "Username2", "Class2");
		AssignFacultyEntity afe1 = afServ.findByPk(2);
		assertEquals(afe, afe1);
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.findByPk(200);
		});
	}

	@Test
	// @Disabled
	public void findByNameTest() throws Exception {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(1, 11, "Username1", "Class1");
		AssignFacultyEntity afe1 = afServ.findByName("Username1");
		AssignFacultyEntity afe2 = afServ.findByName("username5");
		assertEquals(afe, afe1);
		assertNotEquals(afe, afe2);
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.findByName("username1");
		});
	}

	@Test
	// @Disabled
	public void addTest() throws Exception {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(6,16,"username6",106,"coursename6",1006,"subject6","semester6","class6");
		AssignFacultyEntity afe = new AssignFacultyEntity(6, 16, "Username6", "Class6");
		long id = afServ.add(afe);
		AssignFacultyEntity afe1 = afServ.findByPk(1);
		AssignFacultyEntity afe2 = afServ.findByPk(id);
		assertEquals(6, id);
		assertNotEquals(afe, afe1);
		assertEquals(afe, afe2);
		assertThrows(DuplicateRecordException.class, () -> {
			afServ.add(afe);
		});
	}

	@Test
	// @Disabled
	public void updateTest() throws Exception {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(1,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(1, 11, "Username1", "Class1");
		afServ.update(afe);
		AssignFacultyEntity afe1 = afServ.findByPk(1);
		// AssignFacultyEntity afe2 = new
		// AssignFacultyEntity(100,11,"Username1",101,"Coursename1",1001,"Subject1","Semester1","Class1");
		AssignFacultyEntity afe2 = new AssignFacultyEntity(1, 11, "Username1", "Class1");
		assertEquals(afe, afe1);
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.update(afe2);
		});
	}

	@Test
	@Disabled
	public void deleteTest() throws Exception {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(6,16,"username6",106,"coursename6",1006,"subject6","semester6","class6");
		AssignFacultyEntity afe = new AssignFacultyEntity(6, 16, "Username6", "Class6");
		afServ.delete(afe);
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.findByPk(6);
		});
	}

	@Test
	// @Disabled
	public void searchByPagesTest() {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(2,12,"username2",102,"coursename2",1002,"subject2","semester2","class2");
		AssignFacultyEntity afe = new AssignFacultyEntity(2, 12, "Username2", "Class2");
		List<AssignFacultyEntity> al1 = afServ.search(afe, 0, 2);
		List<AssignFacultyEntity> al2 = afServ.search(afe, 1, 2);
		assertEquals(2, al1.size());
		assertEquals(1, al2.size());
	}

	@Test
	// @Disabled
	public void searchTest() {
		// AssignFacultyEntity afe = new
		// AssignFacultyEntity(2,12,"username2",102,"coursename2",1002,"subject2","semester2","class2");
		AssignFacultyEntity afe = new AssignFacultyEntity(2, 12, "Username2", "Class2");
		List<AssignFacultyEntity> al = afServ.search(afe);
		assertEquals(3, al.size());
	}

}