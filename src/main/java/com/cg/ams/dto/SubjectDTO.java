package com.cg.ams.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cg.ams.entity.CourseEntity;
import com.cg.ams.entity.SubjectEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class SubjectDTO {
	@Id
    private long id;
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;
    @Size(min = 2, max = 6)
    private String subjectCode;
    @NotEmpty(message = "Semester shouldn't be empty")
    private String semester;
    
    private CourseInputDTO course;
    
    
    //constructors
    public SubjectDTO(long id,String name,String subjectCode,String semester,CourseInputDTO course) {
    	this.id = id;
    	this.name = name;
    	this.semester = semester;
    	this.subjectCode = subjectCode;
    	this.course = course;
    }
    
    public SubjectDTO(SubjectEntity entity) {
    	this.id=entity.getId();
    	this.name=entity.getName();
    	this.semester=entity.getSemester();
    	this.subjectCode=entity.getSubjectCode();
    	this.course=new CourseInputDTO(entity.getCourse());
    }
    
}