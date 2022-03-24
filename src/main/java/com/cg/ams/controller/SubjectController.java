package com.cg.ams.controller;

import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subServ;

    @GetMapping("/subjects")
    List<SubjectEntity> getAllEmployees() {
        return subServ.getAllSubjects();
    }

    @PostMapping("/subject")
    Long addSubject(@Valid @RequestBody SubjectEntity sub) {
        Long newSubId = subServ.add(sub);
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
    ResponseEntity<SubjectEntity> getSubjectByName(@PathVariable String name) throws Exception {
        SubjectEntity sub = subServ.findByName(name);
        return new ResponseEntity<>(sub, HttpStatus.OK);
    }

    @GetMapping("/subject/{id}")
    ResponseEntity<SubjectEntity> getSubjectById(@PathVariable long id) throws RecordNotFoundException {
        SubjectEntity sub = subServ.findByPk(id);
        return new ResponseEntity<>(sub, HttpStatus.OK);
    }


}
