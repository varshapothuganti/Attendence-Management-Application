package com.cg.ams.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.service.IRoleService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private long id;

	private String firstName;
	private String lastName;

	@Column(nullable = false, unique = true)
	private String login;
	private String password;

	private Date dob;
	private String mobileNo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	private String gender;
	private String profilePic;

	// Special contructor from DTO object
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

		this.profilePic = userInputDTO.getProfilePic();
		this.role = userInputDTO.getRole();
	}

}
