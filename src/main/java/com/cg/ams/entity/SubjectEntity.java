package com.cg.ams.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cg.ams.dto.SubjectDTO;

@Entity
@Data
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {

	@Id
    private long id;
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;
    @Size(min = 2, max = 6)
    private String subjectCode;
    @NotEmpty(message = "Semester shouldn't be empty")
    private String semester;
    
    //constructors
    public SubjectEntity(long id, String name, String subjectCode, String semester) {
    	this.id=id;
    	this.name=name;
    	this.subjectCode=subjectCode;
    	this.semester=semester;
	}
 
    
    public SubjectEntity(SubjectDTO subjectDTO) {
		this.id = subjectDTO.getId();
		this.name = subjectDTO.getName();
		this.course = subjectDTO.getCourse();
		this.subjectCode = subjectDTO.getSubjectCode();
		this.semester = subjectDTO.getSemester();
	}


	@ManyToOne(cascade = CascadeType.ALL)
    private CourseEntity course;
    



}
