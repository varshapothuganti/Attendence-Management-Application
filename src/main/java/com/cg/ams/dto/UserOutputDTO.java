package com.cg.ams.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {
	
	private long id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@Length(min = 3, max = 25, message = "login ID length should between 3 and 25")
	private String login;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String mobileNo;
	private String gender;
	private long roleId;
	
	public UserOutputDTO(UserEntity user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.login = user.getLogin();
		this.dob = user.getDob();
		this.gender = user.getGender();
		this.mobileNo = user.getMobileNo();
		this.roleId = user.getRoleId();
	}
	
}