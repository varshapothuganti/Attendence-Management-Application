package com.cg.ams.service;

import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
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

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;
import com.cg.ams.dto.CourseInputDTO;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.entity.RoleEntity;
import com.cg.ams.entity.UserEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.IAssignFacultyRepository;
import com.cg.ams.repository.IRoleRepository;
import com.cg.ams.repository.IUserRepository;

/*
 * A Test class for testing Assign Faculty service methods with Mockito annotations.
 * @Author Ramu
 */

@ExtendWith(SpringExtension.class)
public class AssignFacultyServiceMockitoTest {

	@InjectMocks
	AssignFacultyServiceImpl afServ;

	@MockBean
	IAssignFacultyRepository afRep;

	@MockBean
	IUserRepository userRep;

	@MockBean
	IRoleRepository roleRep;

	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	// @Disabled
	public void addTest() throws Exception {
		RoleEntity role = new RoleEntity(11, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		Mockito.when(userRep.findById(1L)).thenReturn(Optional.of(new UserEntity(userInDTO)));
		Mockito.when(roleRep.getById(11L)).thenReturn(role);
		long l = afServ.add(afInDTO);
		assertEquals(1111, l);
	}

	@Test
	// @Disabled
	public void findByPkTest() throws Exception {
		RoleEntity role = new RoleEntity(11, "name1", "description1");
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(1111);
		assertEquals("firstName1 lastName1", afOutDTO.getUsername());
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.findByPk(2);
		});
	}

	@Test
	// @Disabled
	public void findByNameTest() throws Exception {
		RoleEntity role = new RoleEntity(11, "name1", "description1");
		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findByUserName("firstName1 lastName1")).thenReturn(afe);
		AssignFacultyOutputDTO afOutDTO = afServ.findByName("firstName1 lastName1");
		assertEquals(1111, afOutDTO.getId());
		assertThrows(RecordNotFoundException.class, () -> {
			afServ.findByName("abc");
		});
	}

	@Test
	// @Disabled
	public void updateTest() throws Exception {
		RoleEntity role = new RoleEntity(11, "name1", "description1");

		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.save(afe)).thenReturn(afe);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		Mockito.when(userRep.findById(1L)).thenReturn(Optional.of(new UserEntity(userInDTO)));
		Mockito.when(roleRep.getById(11L)).thenReturn(role);
		afServ.update(afInDTO);
		assertEquals("firstName1 lastName1", afServ.findByPk(1111).getUsername());
	}

	@Test
	// @Disabled
	public void deleteTest() throws Exception {
		RoleEntity role = new RoleEntity(11,"name1","description1");

		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		Mockito.when(afRep.findById(1111L)).thenReturn(Optional.of(afe));
		afServ.delete(afInDTO);
		Mockito.verify(afRep).deleteById(afe.getId());
	}

	@Test
	// @Disabled
	public void searchByPagesTest() throws Exception {
		RoleEntity role = new RoleEntity(11,"name1","description1");

		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> afList = new ArrayList<>();
		afList.add(afe);
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName(), PageRequest.of(0, 1))).thenReturn(afList);
		List<AssignFacultyOutputDTO> al = afServ.search(afInDTO, 0, 1);
		List<AssignFacultyOutputDTO> al1 = afServ.search(afInDTO, 1, 1);
		assertEquals(1, al.size());
		assertEquals(0, al1.size());
	}

	@Test
	// @Disabled
	public void searchTest() throws Exception {
		RoleEntity role = new RoleEntity(11,"name1","description1");

		UserInputDTO userInDTO = new UserInputDTO(1L, "firstName1", "lastName1", "login1", "password1", "password1",
				new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-29T11:04:54.511Z"), "mobileNo1", "gender1",
				"profilePic1", role);
		CourseInputDTO c1 = new CourseInputDTO(101, "name1", "description1");
		SubjectDTO subDTO1 = new SubjectDTO(111, "subjectName1", "code1", "semester1", c1);
		SubjectDTO subDTO2 = new SubjectDTO(112, "subjectName2", "code2", "semester2", c1);
		List<SubjectDTO> subList = new ArrayList<>();
		subList.add(subDTO1);
		subList.add(subDTO2);
		AssignFacultyInputDTO afInDTO = new AssignFacultyInputDTO(1111, userInDTO, subList, "class1");
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> afList = new ArrayList<>();
		afList.add(afe);
		Mockito.when(afRep.findByUserNameIgnoreCase(afe.getUserName())).thenReturn(afList);
		List<AssignFacultyOutputDTO> al = afServ.search(afInDTO);
		assertEquals(1, al.size());
	}

}