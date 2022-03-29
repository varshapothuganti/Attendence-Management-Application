package com.cg.ams.dto;

import java.util.Date;

import com.cg.ams.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {
	private long id;
	private String firstName;
	private String lastName;
	private String login;
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
		this.mobileNo = user.getMobileNo();
		this.gender = user.getGender();
//		this.roleId = user.getRole().getId();
	}
}
