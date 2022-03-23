package com.cg.ams.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Attendance {

    @Id
    private long id;
    private long subjectId;
    private String subjectName;

    private long studentId;
    private String studentName;
    private String semester;
    private Date date;
    private String totalClass;
    private long status;
    private long total;
    private String percentage;

    private long courseId;
    private String courseName;
}
