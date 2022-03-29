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

import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.entity.CourseEntity;
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
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1000l, "Signals and Systems", "A12478", "3rd semester",c1);
		SubjectEntity sub=new SubjectEntity(subdto);
		Mockito.when(subRepo.save(sub)).thenReturn(sub);
		Mockito.when(subRepo.findById(1000l)).thenReturn(Optional.of(sub));
		SubjectDTO sub1 = subServ.findByPk(1000);
		assertEquals("Signals and Systems", sub1.getName());
		assertEquals("A12478", sub1.getSubjectCode());
		assertEquals("3rd semester", sub1.getSemester());
	}

	@Test
	void findByNameTest() throws Exception {
		SubjectEntity sub = new SubjectEntity(1000l, "Signals and Systems", "A12478", "3rd semester");
		Mockito.when(subRepo.findByName("Signals and Systems")).thenReturn(Optional.of(sub));
		SubjectDTO sub1 = subServ.findByName("Signals and Systems");
		assertEquals(1000l, sub1.getId());
		assertEquals("3rd semester", sub1.getSemester());
		assertEquals("A12478", sub1.getSubjectCode());
	}

	@Test
	void updateTest() throws RecordNotFoundException {
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1000, "Signals and Systems", "B14K28", "6th semester",c1);
		SubjectEntity sub=new SubjectEntity(subdto);
		Mockito.when(subRepo.save(sub)).thenReturn(sub);
		Mockito.when(subRepo.findById(1000l)).thenReturn(Optional.of(sub));
		subServ.update(subdto);
		SubjectDTO sub1 = subServ.findByPk(1000);
		assertEquals("6th semester", sub1.getSemester());
		assertEquals("B14K28", sub1.getSubjectCode());
		assertEquals("Signals and Systems", sub1.getName());
	}

	@Test
	void findByPkTest() throws RecordNotFoundException {
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1000, "Signals and Systems", "B14K28", "6th semester",c1);
		SubjectEntity sub=new SubjectEntity(subdto);
		Mockito.when(subRepo.findById(1000l)).thenReturn(Optional.of(sub));
		SubjectDTO sub1 = subServ.findByPk(1000);
		assertEquals("Signals and Systems", sub1.getName());
		assertEquals("6th semester", sub1.getSemester());
		assertEquals("B14K28", sub1.getSubjectCode());
	}
	
	@Test
	void searchPageTest() {
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1100, "Signals and Systems", "B14K28", "6th Semester",c1);
		SubjectDTO subdto1=new SubjectDTO(2000, "Signals and Systems", "A4312", "3rd Semester",c1);
		SubjectEntity sub=new SubjectEntity(subdto1);
		Mockito.when(subRepo.save(sub)).thenReturn(sub);
		subServ.add(subdto1);
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		list.add(subdto1);
		list.add(subdto);
		Mockito.when(subRepo.findByNameIgnoreCase(sub.getName(), PageRequest.of(0, 3))).thenReturn(list);
		List<SubjectDTO> al1 = subServ.search("Signals and Systems", 0, 3);
		assertEquals(2, al1.size());
	}

	@Test
	void deleteTest() throws RecordNotFoundException {
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1000, "Signals and Systems", "B14K28", "6th semester",c1);
		SubjectEntity sub=new SubjectEntity(subdto);
		Mockito.when(subRepo.findById(1000l)).thenReturn(Optional.of(sub));
		subServ.delete(subdto);
	}



	@Test
	void searchTest() {
		CourseEntity c1=new CourseEntity(111,"Communicatons","This is an Interesting subject");
		SubjectDTO subdto = new SubjectDTO(1100, "Signals and Systems", "B14K28", "6th Semester",c1);
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		list.add(subdto);
		SubjectEntity sub=new SubjectEntity(subdto);
		Mockito.when(subRepo.save(sub)).thenReturn(sub);
		subServ.add(subdto);
		List<SubjectDTO> l = subServ.search("Signals and Systems");
		assertEquals(1, list.size());
	}

}