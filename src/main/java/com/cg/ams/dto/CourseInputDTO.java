package com.cg.ams.dto;

import com.cg.ams.entity.CourseEntity;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInputDTO {

	private long id;
	private String name;
	private String description;

	public CourseInputDTO(CourseEntity course) {
		this.id = course.getId();
		this.name = course.getName();
		this.description = course.getDescription();
	}
}
