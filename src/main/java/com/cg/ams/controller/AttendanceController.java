package com.cg.ams.controller;

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

import com.cg.ams.bean.Attendance;
import com.cg.ams.service.IAttendanceService;

@RestController
public class AttendanceController {
	@Autowired
	IAttendanceService attServ;

	@PostMapping("/attendance")
	ResponseEntity<Attendance> add(@RequestBody Attendance att) {
		Attendance newAtt = attServ.add(att);
		return new ResponseEntity<>(newAtt, HttpStatus.CREATED);
	}

	@GetMapping("/attendance/{id}")
	Attendance findByPk(@PathVariable("id") int id) {
		return attServ.findByPk(id);
	}

	@GetMapping("/attendance/byname/{student_name}")
	Attendance findByPk(@PathVariable("student_name") String student_name) {
		return attServ.findByName(student_name);
	}

	@DeleteMapping("/attendancedeletion/")
	void delete(@RequestBody Attendance entity) {
		 attServ.delete(entity);
		
		
	}

	@PutMapping(path = "/updateattendance")
	public void update(@RequestBody Attendance att) {
		attServ.update(att);

	}

}
