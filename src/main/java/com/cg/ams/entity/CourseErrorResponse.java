package com.cg.ams.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
