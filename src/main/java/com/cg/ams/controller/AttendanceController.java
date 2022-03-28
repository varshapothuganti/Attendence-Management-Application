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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.entity.Attendance;
import com.cg.ams.service.IAttendanceService;

@RestController
public class AttendanceController {
	@Autowired
	IAttendanceService attServ;

	@PostMapping("/attendance/add")
	ResponseEntity<Attendance> add(@RequestBody Attendance att) {
		Attendance newAtt = attServ.add(att);
		return new ResponseEntity<>(newAtt, HttpStatus.CREATED);
	}

	@GetMapping("/attendance/{id}")
	Attendance findByPk(@PathVariable("id") int id) {
		return attServ.findByPk(id);
	}

	@GetMapping("/attendance/byname/{student_name}")
	Attendance findByName(@PathVariable("student_name") String student_name) {
		Attendance newAtt = attServ.findByName(student_name);
		return newAtt;
	}

	@DeleteMapping("/attendance/deletion")
	void delete(@RequestBody Attendance entity) {
		attServ.delete(entity);

	}

	@PutMapping(path = "/attendance/update")
	public void update(@RequestBody Attendance att) {
		attServ.update(att);
	}

	@GetMapping(path = "attendance/search/{name}")
	ResponseEntity<List<Attendance>> search(@PathVariable String name,
			@RequestParam(value = "page", defaultValue = "0") int pageNo,
			@RequestParam(value = "size", defaultValue = "10") int pageSize) {
		return new ResponseEntity<>(attServ.search(name, pageNo, pageSize), HttpStatus.OK);
	}

}
