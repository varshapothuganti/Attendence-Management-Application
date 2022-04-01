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
import javax.persistence.OneToOne;
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
	
    @NotEmpty(message="studentname shouldn't be empty")
    private String firstName;
    
    @NotEmpty(message="studentname shouldn't be empty")
    private String lastName;
    
	private long courseId;
	
	@NotEmpty(message = "Course Name shouldn't be empty")
	private String courseName;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private String gender;
	
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
	
	
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	name = "student_subject",
	joinColumns = { @JoinColumn(name = "std_id") },
	inverseJoinColumns = { @JoinColumn(name = "subject_id") }
	)
	private List<SubjectEntity> subject;
	
	
    @OneToOne(cascade = CascadeType.MERGE)
    private UserEntity user;
    
    //Constructor from DTO object
	public StudentEntity(StudentInputDTO stdDTO) {
		this.id = stdDTO.getId();
		this.rollNo=stdDTO.getRollNo();
		this.subject = new ArrayList<>();
		this.user = new UserEntity(stdDTO.getUserDTO());
		for(int i = 0;i < stdDTO.getSubDTO().size();i++) {
			this.subject.add(new SubjectEntity(stdDTO.getSubDTO().get(i)));
		}
		this.firstName = this.user.getFirstName();
		this.lastName=this.user.getLastName();
		this.dob=this.user.getDob();
		this.gender=this.user.getGender();
		this.mobileNo=this.user.getMobileNo();
		this.courseId = this.subject.get(0).getCourse().getId();
		this.courseName = this.subject.get(0).getCourse().getName();
		this.fatherEmailId=stdDTO.getFatherEmailId();
		this.fatherMobileNo=stdDTO.getFatherMobileNo();
		this.emailId=stdDTO.getEmailId();
		this.profilePic=this.user.getProfilePic();

	}
	
	
	
	
}
