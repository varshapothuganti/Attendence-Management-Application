package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * This class respresent the User Entity that is persisted into the database
 *
 * @author phanindra duvvuri
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

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Length(min = 3, max = 25, message = "login ID length should between 3 and 25")
    private String login;
    @Length(min = 8, max = 40, message = "Password lenght should be between 8 and 40")
    private String password;

    @Transient
    private String confirmPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String mobileNo;
    @Min(value = 1, message = "minimum role ID is 1")
    private long roleId;
    private String gender;
    private String profilePic;
}
