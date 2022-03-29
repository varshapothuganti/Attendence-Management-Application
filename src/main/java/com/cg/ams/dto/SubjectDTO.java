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
@AllArgsConstructor
public class SubjectDTO {
	@Id
    private long id;
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;
    @Size(min = 2, max = 6)
    private String subjectCode;
    @NotEmpty(message = "Semester shouldn't be empty")
    private String semester;
    
    private CourseEntity course;
    
    public SubjectDTO(SubjectEntity sub) {
    	this.id = sub.getId();
    	this.name = sub.getName();
    	this.course = sub.getCourse();
    	this.semester = sub.getSemester();
    	this.subjectCode = sub.getSubjectCode();
    }
    
}