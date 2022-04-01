package com.cg.ams.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentInputDTO {
	
	@NotNull
	private long id;
	
	@Positive(message="Id cannot be less than or equal to zero")
	private long rollNo;
	
	@NotEmpty
	@Email(message = "Provide valid EmailId for Student")
	private String emailId;
	
	@Email(message = "Provide valid EmailId for Parent")
	private String fatherEmailId;
	
	
	@Pattern(regexp ="^(9|7|8)([0-9]){9}$", message="parent Mobile Number is Not valid")
	private String fatherMobileNo;
	
	private UserInputDTO userDTO;
	private List<SubjectDTO> subDTO;
	
	public StudentInputDTO(@NotNull long id, long rollNo,String emailId, UserInputDTO userDTO,
			List<SubjectDTO> subDTO, @Email(message = "Provide valid EmailId for Parent") String fatherEmailId, @Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "parent Mobile Number is Not valid") String fatherMobileNo) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.emailId = emailId;
		this.fatherEmailId=fatherEmailId;
		this.fatherMobileNo=fatherMobileNo;
		this.userDTO = userDTO;
		this.subDTO = subDTO;
	}


	
	
	
	
	
	
	
	

}
