package com.example.demo.com.cg.ams.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class SubjectEntity{
	
	
    @Id
    //@NotEmpty(message="Id cannot be a null value")
	private long id;
	//private long courseId;
	//private String courseName;
    @NotEmpty(message="Name shouldn't be empty")
	private String name;
    @NotEmpty(message="subjectCode shouldn't be empty")
    @Size(min=2,max=6)
	private String subjectCode;
    @NotEmpty(message="Semester shouldn't be empty")
	private String semester;
	//private String description;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private CourseEntity course;
	
	
}
