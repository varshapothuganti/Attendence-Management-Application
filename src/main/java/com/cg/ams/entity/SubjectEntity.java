package com.cg.ams.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 
    
    @ManyToOne(cascade = CascadeType.ALL)
    private CourseEntity course;
    
    /*@ManyToMany(fetch = FetchType.EAGER,mappedBy="subjects")
    private List<AssignFacultyEntity>subjects;*/



}
