package com.cg.ams.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.entity.SubjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentOutputDTO {
	
	@NotNull
	private long id;
	
	@Positive(message="Id cannot be less than or equal to zero")
	private long rollNo;
	
	@NotEmpty(message = " First Name of Student shouldn't be empty")
	private String firstName;
	
	
	@NotEmpty(message="student mobile number should not be empty")
	@Pattern(regexp ="^(9|7|8)([0-9]){9}$", message="Student Mobile Number is Not valid")
	private String mobileNo;

	
	@NotEmpty(message="Profilepic shouldn't be empty")
	private String profilePic;
	
	private List<SubjectDTO> subDTO;
	public StudentOutputDTO(long id, long rollNo, String firstName, String lastName, @Past Date dob,String gender, String mobileNo,String emailId, String fatherEmailId,String fatherMobileNo,String profilePic, List<SubjectDTO> subDTO) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.mobileNo = mobileNo;
		this.profilePic = profilePic;
		this.subDTO = subDTO;
	}
	public StudentOutputDTO(StudentEntity student) {
		this.id = student.getId();
		this.rollNo = student.getRollNo();
		this.firstName = student.getFirstName();
		this.mobileNo = student.getMobileNo();
		this.profilePic = student.getProfilePic();
		this.subDTO = new ArrayList<>();
		for(SubjectEntity sub : student.getSubject()) {
			this.subDTO.add(new SubjectDTO(sub));
		}
	}

}
