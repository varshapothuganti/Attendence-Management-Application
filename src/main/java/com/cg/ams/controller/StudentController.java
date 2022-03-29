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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.entity.StudentEntity;
import com.cg.ams.service.IStudentService;




@RestController

public class StudentController {
	
	@Autowired
	IStudentService studentService;
	
	@PostMapping("/add")
	long addStudent(@Valid @RequestBody StudentEntity std) {
		return studentService.add(std);
	}
	@PutMapping("/update")
	void updateStudent(@Valid @RequestBody StudentEntity std)
	{
		studentService.update(std);
	}
	@DeleteMapping("/delete")
	void deleteStudent(@Valid @RequestBody StudentEntity std)
	{
		studentService.delete(std);
	}
	@GetMapping("/students/byPk/{id}")
	StudentEntity getStudentById(@Valid @PathVariable("id") int stdId)
	{
		return studentService.findByPk(stdId);
		
	}
	
	@GetMapping(path = "/search/{name}")
	ResponseEntity<List<StudentEntity>> search(@RequestParam("name") String name) {

		return new ResponseEntity<>(studentService.search(name), HttpStatus.OK);
	}

	@GetMapping("/students/byName/{name}")
	ResponseEntity<List<StudentEntity>> getStdByName(@Valid @PathVariable("name") String name) {
		List<StudentEntity> std = studentService.findByName(name);
		return new ResponseEntity<>(std, HttpStatus.OK);
	}

    @GetMapping(path = "/search/byPages/{name}")
    ResponseEntity<List<StudentEntity>> search(@Valid @PathVariable String name,
                                            @RequestParam(value = "page", defaultValue = "0") int pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(studentService.search(name, pageNo, pageSize), HttpStatus.OK);
    }
    


}
