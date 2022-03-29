package com.cg.ams.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.entity.RoleEntity;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@Length(min = 3, max = 25, message = "login ID length should between 3 and 25")
	private String login;
	@Length(min = 8, max = 40, message = "Password lenght should be between 8 and 40")
	private String password;
	@Length(min = 8, max = 40, message = "Password lenght should be between 8 and 40")
	private String confirmPassword;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String mobileNo;
	private String gender;
	private RoleEntity role;
}
