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

	@GetMapping("/subjects")
	List<SubjectEntity> getAllEmployees() {
		return subServ.getAllSubjects();
	}

	@PostMapping("/subject")
	Long addSubject(@Valid @RequestBody SubjectEntity sub) {
		return subServ.add(sub);
	}

	@DeleteMapping("/subject")
	ResponseEntity<String> deleteSubject(@Valid @RequestBody SubjectEntity sub) {
		subServ.delete(sub);
		return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
	}

	@PatchMapping("/subject")
	ResponseEntity<String> updateSubject(@Valid @RequestBody SubjectEntity sub) {
		subServ.update(sub);
		return new ResponseEntity<>("Subject Updated successfully", HttpStatus.OK);

	}

	@GetMapping("/subject/byname/{name}")
	ResponseEntity<SubjectEntity> getSubjectByName(@PathVariable String name) {
		SubjectEntity sub = subServ.findByName(name);
		return new ResponseEntity<>(sub, HttpStatus.OK);
	}

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
