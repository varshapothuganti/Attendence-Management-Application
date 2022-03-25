package com.cg.ams.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id
	@GeneratedValue
	private long id;
	private long rollNo;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String mobileNo;
	private long courseId;
	private String courseName;
	private long subjectId;
	private String subjectName;
	private String semester;
	private String emailId;
	private String fatherEmailId;
	private String fatherMobileNo;
	private String profilePic;
	

}
