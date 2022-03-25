package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    public CourseEntity(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    
    @OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="subjectId")
	private List<SubjectEntity> subject;

}
