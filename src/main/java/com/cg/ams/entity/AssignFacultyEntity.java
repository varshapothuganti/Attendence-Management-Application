package com.cg.ams.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignFacultyEntity{
	
	@Id
	private long id;
	
	@NotEmpty(message="User Id shouldn't be empty!")
	private long userId;
	
	@NotEmpty(message="User name shouldn't be empty!")
	@Min(value=8,message="User name shouldn't be less than 8 characters")
	@Max(value=100,message="User name shouldn't be more than 100 characters")
	private String userName;
	
	@NotEmpty(message="Course Id shouldn't be empty!")
	private long courseId;
	
	@NotEmpty(message="Course name shouldn't be empty!")
	@Min(value=8,message="Course name shouldn't be less than 8 characters")
	@Max(value=100,message="Course name shouldn't be more than 100 characters")
	private String courseName;
	
	@NotEmpty(message="Subject Id shouldn't be empty!")
	private long subjectId;
	
	@NotEmpty(message="Subject name shouldn't be empty!")
	@Min(value=8,message="Subject name shouldn't be less than 8 characters")
	@Max(value=100,message="Subject name shouldn't be more than 100 characters")
	private String subjectName;
	
	private String semester;
	private String totalClass;

}
