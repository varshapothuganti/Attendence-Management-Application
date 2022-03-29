package com.cg.ams.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.RecordNotFoundException;


@SpringBootTest
public class SubjectServiceTest {
	
   @Autowired
   ISubjectService subServ;
   
   @Test
   void addTest() throws RecordNotFoundException {
	   SubjectEntity sub=new SubjectEntity(1000,"Analog Signals","A12478","3rd semester");
	   subServ.add(sub);
	   SubjectEntity sub1=subServ.findByPk(1000);
	   assertEquals("Analog Signals",sub1.getName());
	   assertEquals("3rd semester",sub1.getSemester());
   }
   
   @Test
   void findByNameTest() throws Exception {
	   SubjectEntity sub=subServ.findByName("Analog Signals");
	   assertEquals(1000,sub.getId());
	   assertEquals("3rd semester",sub.getSemester());
	   assertEquals("A12478",sub.getSubjectCode());  
   }
   
  @Test
   void updateTest() throws RecordNotFoundException {
	   SubjectEntity sub=new SubjectEntity(1000,"Analog Signals","B14K28","6th semester");
	   subServ.update(sub);
	   SubjectEntity sub1=subServ.findByPk(1000);
	   assertEquals("6th semester",sub1.getSemester());
	   assertEquals("B14K28",sub1.getSubjectCode());  
   }
   
   @Test
   void findByPkTest() throws RecordNotFoundException {
	   SubjectEntity sub=subServ.findByPk(1000);
	   assertEquals("Analog Signals",sub.getName());
	   assertEquals("6th semester",sub.getSemester());
	   assertEquals("B14K28",sub.getSubjectCode());
   }
   
   @Test
   void deleteTest() throws RecordNotFoundException {
	   SubjectEntity sub=subServ.findByPk(1000);
	   subServ.delete(sub);
	   assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(()->{subServ.findByPk(1000);});
   }
   
   @Test
   void searchPageTest() {
	    SubjectEntity sub=new SubjectEntity(1100,"Signals and Systems","B14K28","6th Semester");
	    subServ.add(sub);
	    SubjectEntity sub1=new SubjectEntity(2000,"Signals and Systems","A4312","3rd Semester");
	    subServ.add(sub1);
	    List<SubjectEntity> al1 = subServ.search(sub, 0, 3);
		assertEquals(2,al1.size());
   }
   
   @Test
   void searchTest() {
	   SubjectEntity sub1=new SubjectEntity(2300,"Signals and Systems","A4312","3rd Semester");
	   List<SubjectEntity> sublist=subServ.search(sub1);
	   assertEquals(2,sublist.size());
   }
   

}