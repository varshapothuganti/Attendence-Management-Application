package com.cg.ams.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Assign Faculty entity's input DTO class with user input DTO as a member
 * @Author Ramu
 */

@Data
@NoArgsConstructor
public class AssignFacultyInputDTO {
	
	@NotNull
	private long id;
	
	private UserInputDTO userDTO;
	
	private List<SubjectDTO> subDTO;
	
	@NotEmpty(message="totalClass cannot be empty")
    private String totalClass;

	
	public AssignFacultyInputDTO(@NotNull long id, UserInputDTO userDTO, List<SubjectDTO> subDTO,
			@NotEmpty(message = "totalClass cannot be empty") String totalClass) {
		this.id = id;
		this.userDTO = userDTO;
		this.subDTO = subDTO;
		this.totalClass = totalClass;
	}

}