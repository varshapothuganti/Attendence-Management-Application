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
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.service.IAssignFacultyService;

@RestController
public class AssignFacultyController {

	@Autowired
	IAssignFacultyService afServ;

	@PostMapping("/faculty/add")
	ResponseEntity<Long> add(@Valid @RequestBody AssignFacultyEntity entity) {
		long l = afServ.add(entity);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@PostMapping("/faculty/update")
	ResponseEntity<AssignFacultyEntity> update(@Valid @RequestBody AssignFacultyEntity entity) {
		afServ.update(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@DeleteMapping("/faculty/delete")
	ResponseEntity<String> delete(@Valid @RequestBody AssignFacultyEntity entity) {
		afServ.delete(entity);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/faculty/getByName/{name}")
	ResponseEntity<AssignFacultyEntity> findByName(@PathVariable("name") String name) {
		AssignFacultyEntity afe = afServ.findByName(name);
		return new ResponseEntity<>(afe, HttpStatus.OK);
	}

	@GetMapping("/faculty/getByPk/{id}")
	ResponseEntity<AssignFacultyEntity> findByPk(@PathVariable("id") long id) {
		AssignFacultyEntity afe = afServ.findByPk(id);
		return new ResponseEntity<>(afe, HttpStatus.OK);
	}

	@GetMapping("/faculty/searchByPages/{pageNo}/{pageSize}")
	List<AssignFacultyEntity> search(@RequestBody AssignFacultyEntity entity, @PathVariable("pageNo") long pageNo,
			@PathVariable("pageSize") int pageSize) {
		return afServ.search(entity, pageNo, pageSize);
	}

	@GetMapping("/faculty/search")
	List<AssignFacultyEntity> search(@RequestBody AssignFacultyEntity entity) {
		return afServ.search(entity);
	}

}