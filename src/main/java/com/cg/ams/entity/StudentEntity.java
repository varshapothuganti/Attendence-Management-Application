package com.cg.ams.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "student")
public class StudentEntity {

	@Id
	@GeneratedValue
	private long id;
	@Positive(message = "Id cannot be less than or equal to zero")
	private long rollNo;

	@NotEmpty(message = " First Name of Student shouldn't be empty")
	private String firstName;
	@NotEmpty(message = "Last Name of Student Shouldn't be empty")
	private String lastName;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@NotEmpty(message = "Gender should not be empty")
	private String gender;
	@NotEmpty(message = "student mobile number should not be empty")
	@Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "Student Mobile Number is Not valid")
	private String mobileNo;
	@Email(message = "Provide valid EmailId for Student")
	private String emailId;

	@Email(message = "Provide valid EmailId for Parent")
	private String fatherEmailId;

	@NotEmpty(message = "parent mobile number should not be empty")
	@Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "parent Mobile Number is Not valid")
	private String fatherMobileNo;

	@NotEmpty(message = "Profilepic shouldn't be empty")
	private String profilePic;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "student_subject", joinColumns = { @JoinColumn(name = "std_id") }, inverseJoinColumns = {
			@JoinColumn(name = "subject_id") })
	private List<SubjectEntity> subject;

	public StudentEntity(long id, @Positive(message = "Id cannot be less than or equal to zero") long rollNo,
			@NotEmpty(message = " First Name of Student shouldn't be empty") String firstName,
			@NotEmpty(message = "Last Name of Student Shouldn't be empty") String lastName, @Past Date dob,
			@NotEmpty(message = "Gender should not be empty") String gender,
			@NotEmpty(message = "student mobile number should not be empty") @Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "Student Mobile Number is Not valid") String mobileNo,
			@Email(message = "Provide valid EmailId for Student") String emailId,
			@Email(message = "Provide valid EmailId for Parent") String fatherEmailId,
			@NotEmpty(message = "parent mobile number should not be empty") @Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "parent Mobile Number is Not valid") String fatherMobileNo,
			@NotEmpty(message = "Profilepic shouldn't be empty") String profilePic) {
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

}
