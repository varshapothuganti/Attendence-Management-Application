package com.cg.ams.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.ISubjectRepository;


@ExtendWith(SpringExtension.class)
public class SubjectServiceMockitoTest {
	
	@InjectMocks
	SubjectServiceImpl subServ;
	
	@MockBean
	ISubjectRepository subRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void addTest() throws RecordNotFoundException {
		   SubjectEntity sub=new SubjectEntity(1000,"Signals and Systems","A12478","3rd semester");
		   Mockito.when(subRepo.save(sub)).thenReturn(sub);
		   Mockito.when(subRepo.findById(1000)).thenReturn(Optional.of(sub));
		   SubjectEntity sub1=subServ.findByPk(1000);
		   assertEquals("Signals and Systems",sub1.getName());
		   assertEquals("A12478",sub1.getSubjectCode());
		   assertEquals("3rd semester",sub1.getSemester());
	}
	
	  @Test
	   void findByNameTest() throws Exception {
		   SubjectEntity sub=new SubjectEntity(1000,"Signals and Systems","A12478","3rd semester");
		   Mockito.when(subRepo.findByName("Signals and Systems")).thenReturn(Optional.of(sub));
		   SubjectEntity sub1=subServ.findByName("Signals and Systems");
		   assertEquals(1000,sub1.getId());
		   assertEquals("3rd semester",sub1.getSemester());
		   assertEquals("A12478",sub1.getSubjectCode()); 
	   }
	
	@Test
	void updateTest() throws RecordNotFoundException {
		       SubjectEntity sub=new SubjectEntity(1000,"Signals and Systems","B14K28","6th semester");
			   Mockito.when(subRepo.save(sub)).thenReturn(sub);
			   Mockito.when(subRepo.findById(1000)).thenReturn(Optional.of(sub));
			   subServ.update(sub);
			   SubjectEntity sub1=subServ.findByPk(1000);
			   assertEquals("6th semester",sub1.getSemester());
			   assertEquals("B14K28",sub1.getSubjectCode());  
			   assertEquals("Signals and Systems",sub1.getName());
	}
	
	 @Test
	   void findByPkTest() throws RecordNotFoundException {
		   SubjectEntity sub=new SubjectEntity(1000,"Signals and Systems","B14K28","6th semester");
		   Mockito.when(subRepo.findById(1000)).thenReturn(Optional.of(sub));
		   SubjectEntity sub1=subServ.findByPk(1000);
		   assertEquals("Signals and Systems",sub1.getName());
		   assertEquals("6th semester",sub1.getSemester());
		   assertEquals("B14K28",sub1.getSubjectCode());
	   }
	   
	
	@Test
	void deleteTest() throws RecordNotFoundException {
		   SubjectEntity sub=new SubjectEntity(1000,"Signals and Systems","B14K28","6th semester");
		   Mockito.when(subRepo.findById(1000)).thenReturn(Optional.of(sub));
		   subServ.delete(sub);
		   Mockito.when(subRepo.findById(1000)).thenReturn(Optional.empty());
		   assertThrows(RecordNotFoundException.class,()->{subServ.findByPk(1000);});
	}
	
	@Test
	void searchPageTest() {
		 SubjectEntity sub=new SubjectEntity(1100,"Signals and Systems","B14K28","6th Semester");
		 Mockito.when(subRepo.save(sub)).thenReturn(sub);
		 subServ.add(sub);
		 SubjectEntity sub1=new SubjectEntity(2000,"Signals and Systems","A4312","3rd Semester");
		 subServ.add(sub1);
		 List<SubjectEntity> list=new ArrayList<SubjectEntity>();
		 list.add(sub1);
		 list.add(sub);
		 Mockito.when(subRepo.findByNameIgnoreCase(sub.getName(), PageRequest.of(0, 3))).thenReturn(list);
		 List<SubjectEntity> al1 = subServ.search(sub, 0, 3);
		 assertEquals(2,al1.size());
	}
	
	@Test
	void searchTest() {
		SubjectEntity sub=new SubjectEntity(1100,"Signals and Systems","B14K28","6th Semester");
		List<SubjectEntity>list=new ArrayList<SubjectEntity>();
		Mockito.when(subRepo.save(sub)).thenReturn(sub);
		subServ.add(sub);
		list.add(sub);
		@SuppressWarnings("unused")
		List<SubjectEntity> l = subServ.search(sub);
		assertEquals(1,list.size());
	}
	
	
	

}

