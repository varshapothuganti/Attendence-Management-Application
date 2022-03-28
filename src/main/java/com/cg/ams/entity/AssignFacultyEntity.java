package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "assign_faculty")
public class AssignFacultyEntity {

    @Id
    private long id;

    @NotNull(message = "User Id shouldn't be empty!")
    private long userId;

    @NotEmpty(message = "User name shouldn't be empty!")
    @Length(min = 8, max = 100, message = "User name length should be between 8 and 100")
    private String userName;

    //@NotNull
    //private long courseId;

    //@NotEmpty(message = "Course name shouldn't be empty!")
    //@Length(min = 8, max = 100, message = "Course name should be between 8 and 100")
    //private String courseName;

    //@NotNull
    //private long subjectId;

    //@NotEmpty(message = "Subject name shouldn't be empty!")
    //@Length(min = 8, max = 100, message = "Subject name should be between 8 and 100")
    //private String subjectName;
    //private String semester;
    
    
    @NotEmpty(message="totalClass cannot be empty")
    private String totalClass;
    
    public AssignFacultyEntity(long id,long userId,String userName,String totalClass){
    	this.id=id;
    	this.userId=userId;
    	this.userName=userName;
    	this.totalClass=totalClass;
    }
    

    @ManyToMany(fetch = FetchType.LAZY,targetEntity=SubjectEntity.class, cascade={CascadeType.ALL})
    @JoinTable(name="Faculty_Subjects", 
            joinColumns=   { @JoinColumn(name="subject_id") },
            inverseJoinColumns= { @JoinColumn(name="faculty_id")} )
	private List<SubjectEntity> subjects;
    
   
}
   

