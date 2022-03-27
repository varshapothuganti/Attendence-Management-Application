package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class StudentServiceTest {
	
	@Autowired
	IStudentService studentService;
	
	@Test
	@Disabled
	void addTest()throws RecordNotFoundException, ParseException{ 
		StudentEntity student=new StudentEntity();
		student.setRollNo(3);
		student.setFirstName("Nemo");
		student.setLastName("Clown");
		student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"));
		student.setGender("male");
		student.setMobileNo("9999999990");
		student.setCourseId(5);
		student.setCourseName("CSE");
		student.setSubjectId(501);
		student.setSubjectName("AI");
		student.setSemester("First");
		student.setEmailId("nemo@gmail.com");
		student.setFatherEmailId("Marlin@gmail.com");
		student.setFatherMobileNo("7896541230");
		student.setProfilePic("pic1.jpg");
		studentService.add(student);
		assertEquals("Nemo", student.getFirstName());
		assertEquals("nemo@gmail.com", student.getEmailId());	
	}
	
	@Test
	void updateTest() throws RecordNotFoundException
	{
        StudentEntity dbStudent = studentService.findByPk(218);

        // Updating value
        String newLastName = "ClownFish";
        dbStudent.setLastName(newLastName);
        studentService.update(dbStudent);

        StudentEntity updatedStudent = studentService.findByPk(218);

        assertEquals(newLastName, updatedStudent.getLastName());
	}
	
	@Test
	@Disabled
	void deleteTest() throws RecordNotFoundException
	{
		StudentEntity dbStudent =studentService.findByPk(220);
		studentService.delete(dbStudent);
		assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(()->{studentService.findByPk(220);});
	}
	@Test
	void findByPkTest() throws RecordNotFoundException{
		StudentEntity student =studentService.findByPk(218);
        assertEquals("Nemo", student.getFirstName());
        assertEquals("ClownFish", student.getLastName());
		
	}
    @Test
    @Disabled
    void searchTest() {
        List<StudentEntity> students = studentService.search("sh");
        assertEquals(2, students.size());
    }
    @Test
    @Disabled
    void searchPageTest() {
 	    List<StudentEntity> al1 = studentService.search("sh",0, 3);
 		assertEquals(2,al1.size());
    }
	
	


	
	
	

}
