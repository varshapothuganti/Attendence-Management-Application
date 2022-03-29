package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import com.cg.ams.dto.UserInputDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * This class respresent the User Entity that is persisted into the database
 *
 * @author phanindra
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "`users`")
public class UserEntity {

	@Id
	//@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private String login;
	private String password;

	private Date dob;
	private String mobileNo;

	private long roleId;

	private String gender;
	private String profilePic;

	// Special constructor from DTO object
	public UserEntity(UserInputDTO userInputDTO) {
		this.id = userInputDTO.getId();
		this.firstName = userInputDTO.getFirstName();
		this.lastName = userInputDTO.getLastName();
		this.login = userInputDTO.getLogin();
		this.password = userInputDTO.getPassword();
		this.dob = userInputDTO.getDob();
		if (this.dob == null) {
			this.dob = new Date();
		}

		this.mobileNo = userInputDTO.getMobileNo();
		this.gender = userInputDTO.getGender();
		this.roleId = userInputDTO.getRoleId();
		this.profilePic = userInputDTO.getProfilePic();
	}
}
