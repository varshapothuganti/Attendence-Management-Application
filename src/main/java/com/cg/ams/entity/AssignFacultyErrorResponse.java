package com.cg.ams.entity;

import java.time.LocalDateTime;

import lombok.Data;

/*
 * Error Response class for Assign Faculty Entity
 * @Author Ramu
 */

@Data
public class AssignFacultyErrorResponse {
	private int status;
	private String msg;
	private LocalDateTime localDateTime;
}
