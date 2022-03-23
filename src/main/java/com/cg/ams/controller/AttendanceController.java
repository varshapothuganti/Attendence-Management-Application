package com.cg.ams.controller;

import com.cg.ams.entity.Attendance;
import com.cg.ams.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
