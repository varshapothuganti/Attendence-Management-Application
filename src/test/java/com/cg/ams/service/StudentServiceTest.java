package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.exception.RecordNotFoundException;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	IStudentService studentService;
	
	@Test
	@Disabled
	void addTest()throws RecordNotFoundException, ParseException{ 
		StudentEntity student=new StudentEntity();
		student.setRollNo(5);
		student.setFirstName("Varsha");
		student.setLastName("Pothuganti");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-21T11:04:54.511Z"));
		student.setGender("female");
		student.setMobileNo("9999999990");
		student.setEmailId("varsha@gmail.com");
		student.setFatherEmailId("father@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		studentService.add(student);
		assertEquals("Varsha", student.getFirstName());
		assertEquals("varsha@gmail.com", student.getEmailId());	
	}
	
	@Test
	@Disabled
	void updateTest() throws RecordNotFoundException
	{
        StudentEntity dbStudent = studentService.findByPk(250);

        // Updating value
        String newLastName = "ClownFish";
        dbStudent.setLastName(newLastName);
        studentService.update(dbStudent);

        StudentEntity updatedStudent = studentService.findByPk(250);

        assertEquals(newLastName, updatedStudent.getLastName());
	}
	
	@Test
	@Disabled
	void deleteTest() throws RecordNotFoundException
	{
		StudentEntity dbStudent =studentService.findByPk(248);
		studentService.delete(dbStudent);
		assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(()->{studentService.findByPk(248);});
	}
	@Test
	@Disabled
	void findByPkTest() throws RecordNotFoundException{
		StudentEntity student =studentService.findByPk(250);
        assertEquals("Nemo", student.getFirstName());
        assertEquals("ClownFish", student.getLastName());
		
	}
	
	@Test
	@Disabled
	void findByName() throws RecordNotFoundException
	{
		List<StudentEntity> students = studentService.findByName("Varsha");
		assertEquals(2, students.size());
	}

    @Test
    @Disabled
    void searchTest() throws ParseException {
 //   	StudentEntity student=new StudentEntity(1000,2,"Varsha","Pothuganti",new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"),"Female","9999999999","varsha@gmail.com","father@gmail.com","7984561230","pic1.jpg");
 	   List<StudentEntity> stdlist=studentService.search("Varsha");
 	   assertEquals(2,stdlist.size());
    }
    @Test
    @Disabled
    void searchPageTest() {
 	    List<StudentEntity> al1 = studentService.search("sh",0, 3);
 		assertEquals(3,al1.size());
    }
	
	



}
