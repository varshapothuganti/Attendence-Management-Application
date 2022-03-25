package com.cg.ams.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EntityErrorResponse {
	
	private int status;
	private String message;
	private LocalDateTime timeStamp;

}
