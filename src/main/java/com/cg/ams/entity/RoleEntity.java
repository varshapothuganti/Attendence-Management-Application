package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity {

    public static final int ADMIN = 1;
    public static final int FACULTY = 2;

    @Id
    private long id;
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;
    @NotEmpty(message = "Description shouldn't be empty")
    private String description;

}
