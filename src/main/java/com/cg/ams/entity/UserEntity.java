package com.cg.ams.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String confirmPassword;
    private Date dob;
    private String mobileNo;
    private long roleId;
    private String gender;
    private String profilePic;
}
