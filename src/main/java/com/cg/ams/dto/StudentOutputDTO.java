package com.cg.ams.dto;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.entity.SubjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentOutputDTO 
{
	
	@NotNull
	private long id;
	
	
	@Positive(message="Id cannot be less than or equal to zero")
	private long rollNo;
	
    @NotEmpty(message="studentname shouldn't be empty")
    private String firstName;
    
    @NotEmpty(message="studentname shouldn't be empty")
    private String LastName;
    
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	private String gender;
	
	private long courseId;
	
	@NotEmpty(message = "Course Name shouldn't be empty")
	private String courseName;
	
	@NotEmpty(message = "student mobile number should not be empty")
	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Student Mobile Number is Not valid")
	private String mobileNo;
	
	@NotEmpty
	@Email(message = "Provide valid EmailId for Student")
	private String emailId;
	
	@Email(message = "Provide valid EmailId for Parent")
	private String fatherEmailId;
	
	@Pattern(regexp ="^(9|7|8)([0-9]){9}$", message="parent Mobile Number is Not valid")
	private String fatherMobileNo;
	
	private String profilePic;
	

	private UserOutputDTO userDTO;
	private List<SubjectDTO> subDTO;

	public StudentOutputDTO(StudentEntity student) {
		this.id = student.getId();
		this.rollNo=student.getRollNo();
		this.firstName = student.getFirstName();
		this.LastName=student.getLastName();
		this.dob=student.getDob();
		this.gender=student.getGender();
		this.mobileNo=student.getMobileNo();
		this.courseId = student.getCourseId();
		this.courseName = student.getCourseName();
		this.subDTO = new ArrayList<>();
		for(SubjectEntity sub : student.getSubject()) {
			this.subDTO.add(new SubjectDTO(sub));
		}
		this.userDTO = new UserOutputDTO(student.getUser());
		this.emailId=student.getEmailId();
		this.fatherEmailId=student.getFatherEmailId();
		this.fatherMobileNo=student.getFatherMobileNo();
		this.profilePic=student.getProfilePic();
	}
}
	
	



