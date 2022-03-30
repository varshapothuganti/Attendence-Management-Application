package com.cg.ams.entity;


import java.util.ArrayList;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.dto.StudentInputDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the student Entity that is persisted into the database
 *
 * @author varsha
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name= "student")
public class StudentEntity {
	
	@Id
	private long id;
	
	@Positive(message="Id cannot be less than or equal to zero")
	private long rollNo;
	
	@NotEmpty(message = " First Name of Student shouldn't be empty")
	private String firstName;
	
	private String lastName;
	
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	private String gender;
	
	@NotEmpty(message="student mobile number should not be empty")
	@Pattern(regexp ="^(9|7|8)([0-9]){9}$", message="Student Mobile Number is Not valid")
	private String mobileNo;
	
	
	@Email(message = "Provide valid EmailId for Student")
	private String emailId;
	
	@Email(message = "Provide valid EmailId for Parent")
	private String fatherEmailId;
	
	@Pattern(regexp ="^(9|7|8)([0-9]){9}$", message="parent Mobile Number is Not valid")
	private String fatherMobileNo;
	
	@NotEmpty(message="Profilepic shouldn't be empty")
	private String profilePic;
	
	
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	name = "student_subject",
	joinColumns = { @JoinColumn(name = "std_id") },
	inverseJoinColumns = { @JoinColumn(name = "subject_id") }
	)
	private List<SubjectEntity> subject;




	public StudentEntity(long id, long rollNo, String firstName,String lastName, @Past Date dob,String gender,String mobileNo,String emailId,String fatherEmailId, String fatherMobileNo,String profilePic) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.fatherEmailId = fatherEmailId;
		this.fatherMobileNo = fatherMobileNo;
		this.profilePic = profilePic;
	}




	public StudentEntity(StudentInputDTO stdDTO) {
		this.id = stdDTO.getId();
		this.rollNo = stdDTO.getRollNo();
		this.firstName = stdDTO.getFirstName();
		this.mobileNo = stdDTO.getMobileNo();
		this.profilePic = stdDTO.getProfilePic();
		this.subject = new ArrayList<>();
		for(int i = 0;i < stdDTO.getSubDTO().size();i++) {
			this.subject.add(new SubjectEntity(stdDTO.getSubDTO().get(i)));
		}
	}
	

	
	
	
	
}
