package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assign_faculty")
public class AssignFacultyEntity {

    @Id
    private long id;

    @NotNull(message = "User Id shouldn't be empty!")
    private long userId;

    @NotEmpty(message = "User name shouldn't be empty!")
    @Length(min = 8, max = 100, message = "User name length should be between 8 and 100")
    private String userName;

    @NotNull(message = "Course Id shouldn't be empty!")
    private long courseId;

    @NotEmpty(message = "Course name shouldn't be empty!")
    @Length(min = 8, max = 100, message = "Course name should be between 8 and 100")
    private String courseName;

    @NotNull(message = "Subject Id shouldn't be empty!")
    private long subjectId;

    @NotEmpty(message = "Subject name shouldn't be empty!")
    @Length(min = 8, max = 100, message = "Subject name should be between 8 and 100")
    private String subjectName;

    private String semester;
    private String totalClass;

}
