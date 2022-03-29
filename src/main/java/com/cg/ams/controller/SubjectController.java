package com.cg.ams.controller;

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

import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.service.ISubjectService;

@RestController
public class SubjectController {

	@Autowired
	ISubjectService subServ;

	// Mapping to get all subjects in the entity
	@GetMapping("/subjects")
	ResponseEntity<List<SubjectEntity>> getAllSubjects() {
		List<SubjectEntity> subjects = subServ.getAllSubjects();
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	// To add a new subject to the database
	@PostMapping("/subject")
	ResponseEntity<Long> addSubject(@Valid @RequestBody SubjectEntity sub) {
		Long newSubId = subServ.add(sub);
		return new ResponseEntity<>(newSubId, HttpStatus.CREATED);
	}

	// To delete a subject
	@DeleteMapping("/subject")
	ResponseEntity<String> deleteSubject(@Valid @RequestBody SubjectEntity sub) {
		subServ.delete(sub);
//        System.out.println("Subject deleted successfully");
		return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
	}

	// To update a given subject
	@PatchMapping("/subject")
	ResponseEntity<String> updateSubject(@Valid @RequestBody SubjectEntity sub) {
		subServ.update(sub);
//		System.out.println("Subject Updated successfully");
		return new ResponseEntity<>("Subject Updated successfully", HttpStatus.OK);
	}

	// To get a subject based on name
	@GetMapping("/subject/byname/{name}")
	ResponseEntity<SubjectEntity> getSubjectByName(@PathVariable String name) {
		SubjectEntity sub = subServ.findByName(name);
		return new ResponseEntity<>(sub, HttpStatus.OK);
	}

	// To get a subject based on id
	@GetMapping("/subject/{id}")
	ResponseEntity<SubjectEntity> getSubjectById(@PathVariable long id) {
		SubjectEntity sub = subServ.findByPk(id);
		return new ResponseEntity<>(sub, HttpStatus.OK);
	}

	@GetMapping("/subject/searchByPages/{pageNo}/{pageSize}")
	List<SubjectEntity> search(@RequestBody SubjectEntity entity, @PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize) {
		return subServ.search(entity, pageNo, pageSize);
	}

	@GetMapping("/subject/search")
	List<SubjectEntity> search(@RequestBody SubjectEntity entity) {
		return subServ.search(entity);
	}
}
