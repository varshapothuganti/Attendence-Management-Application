package com.cg.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.service.IStudentService;




@RestController

public class StudentController {
	
	@Autowired
	IStudentService stdServ;
	
	@PostMapping("/students")
	long addStudent(@RequestBody StudentEntity std) {
		return stdServ.add(std);
	}
	@PutMapping("/students")
	void updateStudent(@RequestBody StudentEntity std)
	{
		stdServ.update(std);
	}
	@DeleteMapping("/students")
	void deleteStudent(@RequestBody StudentEntity std)
	{
		stdServ.delete(std);
	}
	@GetMapping("/students/byPk/{id}")
	StudentEntity getStudentById(@PathVariable("id") int stdId)
	{
		return stdServ.findByPk(stdId);
		
	}
	@GetMapping("/students/byName/{name}")
	ResponseEntity<List<StudentEntity>> getStdByName(@PathVariable("name") String firstName) {
		List<StudentEntity> std = stdServ.findByName(firstName);
		return new ResponseEntity<>(std, HttpStatus.OK);
	}
	


}
