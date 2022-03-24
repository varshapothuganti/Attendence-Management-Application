package com.cg.ams.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull(message="User Id shouldn't be empty!")
	private long userId;
	
	@NotEmpty(message="User name shouldn't be empty!")
	@Size(min=8,max=100,message="User name shouldn't be less than 8 characters and more than 100 characters")
	private String userName;
	
	@NotNull(message="Course Id shouldn't be empty!")
	private long courseId;
	
	@NotEmpty(message="Course name shouldn't be empty!")
	@Size(min=8,max=100,message="User name shouldn't be less than 8 characters and more than 100 characters")
	private String courseName;
	
	@NotNull(message="Subject Id shouldn't be empty!")
	private long subjectId;
	
	@NotEmpty(message="Subject name shouldn't be empty!")
	@Size(min=8,max=100,message="User name shouldn't be less than 8 characters and more than 100 characters")
	private String subjectName;
	
	private String semester;
	private String totalClass;

}
