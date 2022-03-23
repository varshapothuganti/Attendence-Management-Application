package com.cg.ams.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserErrorResponse {
    private int status;
    private String msg;
    private LocalDateTime localDateTime;
}
