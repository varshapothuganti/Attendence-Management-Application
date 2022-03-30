package com.cg.ams.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.cg.ams.dto.AssignFacultyInputDTO;

@Entity
@Data
@NoArgsConstructor
@Table(name = "assign_faculty")
public class AssignFacultyEntity {

    @Id
    private long id;
    
    @NotEmpty(message="Username shouldn't be empty")
    private String userName;
    
    @NotEmpty(message="totalClass cannot be empty")
    private String totalClass;
    
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    
    @ManyToMany(fetch = FetchType.LAZY,targetEntity=SubjectEntity.class, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name="Faculty_Subjects", 
            joinColumns=   { @JoinColumn(name="faculty_id") },
            inverseJoinColumns= { @JoinColumn(name="subject_id")} )
	private List<SubjectEntity> subjects;

	public AssignFacultyEntity(AssignFacultyInputDTO afDTO) {
		this.id = afDTO.getId();
		this.user = new UserEntity(afDTO.getUserDTO());
		this.userName = this.user.getFirstName()+this.user.getLastName();
		this.subjects = new ArrayList<>();
		for(int i = 0;i < afDTO.getSubDTO().size();i++) {
			this.subjects.add(new SubjectEntity(afDTO.getSubDTO().get(i)));
		}
		this.totalClass = afDTO.getTotalClass();
	}
}