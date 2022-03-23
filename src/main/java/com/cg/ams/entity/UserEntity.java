package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @Transient
    private String confirmPassword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String mobileNo;
    private long roleId;
    private String gender;
    private String profilePic;
}
