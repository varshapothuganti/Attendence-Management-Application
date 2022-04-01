package com.cg.ams.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.ams.entity.RoleEntity;
import com.cg.ams.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {
	@NotNull
	@Positive
	private long id;
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

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	@NotEmpty(message = "student mobile number should not be empty")
	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Student Mobile Number is Not valid")
	private String mobileNo;
	private String gender;
	private String profilePic;

	private RoleEntity role;

	public UserInputDTO(UserEntity entity) {
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.login = entity.getLogin();
		this.password = entity.getPassword();
		this.confirmPassword = this.password;
		this.dob = entity.getDob();
		this.gender = entity.getGender();
		this.profilePic = entity.getProfilePic();
		this.mobileNo = entity.getMobileNo();
		this.role = entity.getRole();
	}
}
