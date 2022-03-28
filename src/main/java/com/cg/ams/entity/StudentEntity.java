package com.cg.ams.entity;


import java.util.Date;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("deprecation")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name= "student")
public class StudentEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	private long rollNo;
	
	@NotEmpty(message = " First Name of Student shouldn't be empty")
	private String firstName;
	@NotEmpty(message = "Last Name of Student Shouldn't be empty")
	private String lastName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String gender;
	
	@Length(min = 10, max = 10, message = "Student Mobile number is not Valid")
	private String mobileNo;
	
	private long courseId;
	
	@NotEmpty(message = " Course Name of Student shouldn't be empty")
	private String courseName;

	private long subjectId;
	
	@NotEmpty(message = "Subject Name of Student shouldn't  be empty")
	private String subjectName;
	@NotEmpty(message = "Semester shouldn't be empty")
	private String semester;
	
	@Email(message = "Provide valid EmailId for Student")
	private String emailId;
	@Email(message = "Provide valid EmailId for Parent")
	private String fatherEmailId;
	@Length(min = 10, max = 10, message = "Parent Mobile number is not Valid")
	private String fatherMobileNo;
	private String profilePic;

	

}
