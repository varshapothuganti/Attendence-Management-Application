package com.cg.ams.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.entity.StudentEntity;
import com.cg.ams.service.IStudentService;




@RestController

public class StudentController {
	
	@Autowired
	IStudentService studentService;
	

	
	@PostMapping("/student/add")
	ResponseEntity<Long> add(@Valid @RequestBody StudentInputDTO stdDTO) {
		long l = studentService.add(stdDTO);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	
	@PostMapping("/student/update")
	ResponseEntity<StudentOutputDTO> update(@Valid @RequestBody StudentInputDTO stdDTO) {
		studentService.update(stdDTO);
		StudentEntity student = new StudentEntity(stdDTO);
		StudentOutputDTO stdOutDTO = new StudentOutputDTO(student);
		return new ResponseEntity<>(stdOutDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/student/delete")
	ResponseEntity<String> delete(@Valid @RequestBody StudentInputDTO stdDTO) {
		studentService.delete(stdDTO);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	
	@GetMapping("/student/getByPk/{id}")
	ResponseEntity<StudentOutputDTO> findByPk(@PathVariable("id") long id) {
		StudentOutputDTO stdOutDTO = studentService.findByPk(id);
		return new ResponseEntity<>(stdOutDTO, HttpStatus.OK);
	}
	



	@GetMapping("/student/getByName/{name}")
	ResponseEntity<List<StudentOutputDTO>> findByName(@PathVariable("name") String name) {
		List<StudentOutputDTO> stdOutDTO = studentService.findByName(name);
		return new ResponseEntity<>(stdOutDTO, HttpStatus.OK);
	}
	@GetMapping("/student/search/{name}")
	ResponseEntity<List<StudentOutputDTO>> search(@RequestParam("name") String name) {
		return new ResponseEntity<>(studentService.search(name), HttpStatus.OK);
	}

	@GetMapping("/student/searchByPages/{name}/{pageNo}/{pageSize}")
	List<StudentOutputDTO> search(@PathVariable String name,
			 @RequestParam(value = "page", defaultValue = "0") int pageNo,
			 @RequestParam(value = "size", defaultValue = "10") int pageSize) {
		return studentService.search(name, pageNo, pageSize);
	}




}
