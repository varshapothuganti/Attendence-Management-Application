package com.cg.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    public static final int ADMIN = 1;
    public static final int FACULTY = 2;

    @Id
    private long id;
    private String name;
    private String description;

}
