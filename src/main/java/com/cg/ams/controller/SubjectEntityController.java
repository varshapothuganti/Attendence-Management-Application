package com.example.demo.controller;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.com.cg.ams.entity.SubjectEntity;
import com.example.demo.com.cg.ams.exception.RecordNotFoundException;
import com.example.demo.com.cg.ams.service.SubjectService;

@RestController
public class SubjectEntityController {
	
	@Autowired
	SubjectService subServ;
	
	@GetMapping("/subjects")
	List<SubjectEntity> getAllEmployees() {
		return subServ.getAllSubjects();
	}
	
	@PostMapping("/subject")
	Long addSubject(@Valid @RequestBody SubjectEntity sub) {
		Long newSubId =  subServ.add(sub);
		return newSubId;
	}
	
	@DeleteMapping("/subject")
	void deleteSubject(@RequestBody SubjectEntity sub) throws RecordNotFoundException {
		 subServ.delete(sub);
		 System.out.println("Subject deleted successfully");
	}
	
	@PatchMapping("/subject")
	void updateSubject(@RequestBody SubjectEntity sub) throws RecordNotFoundException {
		subServ.update(sub);
		System.out.println("Subject Updated successfully");	
	}
	
	@GetMapping("/subject/byname/{name}")
	ResponseEntity<SubjectEntity> getSubjectByName(@PathVariable String name) throws Exception{
		SubjectEntity sub=subServ.findByName(name);
		return new ResponseEntity<>(sub, HttpStatus.OK);
	}
	
	@GetMapping("/subject/{id}")
	ResponseEntity<SubjectEntity> getSubjectById(@PathVariable long id) throws RecordNotFoundException{
		SubjectEntity sub=subServ.findByPk(id);
		return new ResponseEntity<>(sub, HttpStatus.OK);
	}
	

}
