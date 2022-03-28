package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.dto.UserInputDTO;
import com.cg.ams.service.IRoleService;
import com.cg.ams.service.RoleServiceImpl;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private String login;
	private String password;

	private Date dob;
	private String mobileNo;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	private String gender;
	private String profilePic;

	// Special contructor from DTO object
	public UserEntity(UserInputDTO userInputDTO, String profilePic) {
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
		this.role = userInputDTO.getRole();
		this.profilePic = profilePic == null || profilePic.length() == 0 ? "default-pic.jpg" : profilePic;
	}
}
