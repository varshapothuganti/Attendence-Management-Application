package com.example.sprint1.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleErrorResponse {
	
	private int status;
	private String message;
	private LocalDateTime timeStamp;

}
