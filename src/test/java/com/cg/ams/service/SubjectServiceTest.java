package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.entity.CourseEntity;
import com.cg.ams.exception.RecordNotFoundException;


@SpringBootTest
public class SubjectServiceTest {
	
   @Autowired
   ISubjectService subServ;
   
   @Test
   void addTest() {
	   CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
	   SubjectDTO sub=new SubjectDTO(1000,"Analog Signals","A12478","3rd semester",c1);
	   subServ.add(sub);
	   SubjectDTO sub1=subServ.findByPk(1000);
	   assertEquals("Analog Signals",sub1.getName());
	   assertEquals("3rd semester",sub1.getSemester());
	   assertEquals(c1,sub1.getCourse());
   }
   
   @Test
   void findByNameTest() {
	   SubjectDTO sub=subServ.findByName("Analog Signals");
	   assertEquals(1000,sub.getId());
	   assertEquals("3rd semester",sub.getSemester());
	   assertThrows(RecordNotFoundException.class,() -> {subServ.findByName("Digital Signals");});
	   assertEquals("A12478",sub.getSubjectCode());  
   }
   
  @Test
   void updateTest() {
	   CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
	   SubjectDTO sub=new SubjectDTO(1000,"Analog Signals","B14K28","6th semester",c1);
	   subServ.update(sub);
	   SubjectDTO sub1=subServ.findByPk(1000);
	   assertEquals("6th semester",sub1.getSemester());
	   assertEquals("B14K28",sub1.getSubjectCode());  
	   assertEquals(c1,sub1.getCourse());
   }
   
   @Test
   void findByPkTest(){
	   SubjectDTO sub=subServ.findByPk(1000);
	   assertEquals("Analog Signals",sub.getName());
	   assertEquals("6th semester",sub.getSemester());
	   assertEquals("B14K28",sub.getSubjectCode());
	   assertThrows(RecordNotFoundException.class,() -> {subServ.findByPk(200);});
   }
   
   @Test
   void deleteTest(){
	   SubjectDTO sub=subServ.findByPk(1000);
	   subServ.delete(sub);
	   assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(()->{subServ.findByPk(1000);});
   }
   
   @Test
   void searchPageTest() {
	    CourseEntity c1=new CourseEntity();
	    SubjectDTO sub=new SubjectDTO(1100,"Signals and Systems","B14K28","6th Semester",c1);
	    subServ.add(sub);
	    SubjectDTO sub1=new SubjectDTO(2000,"Signals and Systems","A4312","3rd Semester",c1);
	    subServ.add(sub1);
	    List<SubjectDTO> al1 = subServ.search("Signals and Systems", 0, 3);
		assertEquals(2,al1.size());
   }
   
   @Test
   void searchTest() {
	   List<SubjectDTO> sublist=subServ.search("Signals and Systems");
	   assertEquals(2,sublist.size());
   }
   

}

