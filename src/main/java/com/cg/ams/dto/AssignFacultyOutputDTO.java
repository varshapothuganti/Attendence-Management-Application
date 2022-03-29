package com.cg.ams.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.entity.SubjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignFacultyOutputDTO {

	@NotNull
	private long id;
	
	private String username;
	
	private UserOutputDTO userDTO;
	
	private List<SubjectDTO> subDTO;
	
	@NotEmpty(message="totalClass cannot be empty")
    private String totalClass;

	public AssignFacultyOutputDTO(AssignFacultyEntity afe) {
		this.id = afe.getId();
		this.subDTO = new ArrayList<>();
		for(SubjectEntity sub : afe.getSubjects()) {
			this.subDTO.add(new SubjectDTO(sub));
		}
		this.userDTO = new UserOutputDTO(afe.getUser());
		this.username = afe.getUserName();
		this.totalClass = afe.getTotalClass();
	}
	
}