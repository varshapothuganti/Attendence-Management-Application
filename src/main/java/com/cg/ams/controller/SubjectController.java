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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.service.ISubjectService;

@RestController
public class SubjectController {

	@Autowired
	ISubjectService subServ;


    // add subject to the database
    @PostMapping("/subject")
    ResponseEntity<Long> addSubject(@Valid @RequestBody SubjectDTO subdto) {
        Long subId = subServ.add(subdto);
        return new ResponseEntity<>(subId,HttpStatus.OK);
    }

    //delete a subject
    @DeleteMapping("/subject")
   ResponseEntity<String> deleteSubject(@Valid @RequestBody SubjectDTO sub){
        subServ.delete(sub);
        return new ResponseEntity<>("Subject deleted successfully",HttpStatus.OK);
    }

    //update a given subject
    @PatchMapping("/subject")
    ResponseEntity<String> updateSubject(@Valid @RequestBody SubjectDTO sub){
        subServ.update(sub);
        return new ResponseEntity<>("Subject Updated successfully",HttpStatus.OK);
    }

    //get subject based on name
    @GetMapping("/subject/byname/{name}")
    ResponseEntity<SubjectDTO> getSubjectByName(@PathVariable String name){
        SubjectDTO sub = subServ.findByName(name);
        return new ResponseEntity<>(sub, HttpStatus.OK);
    }

    // get subject based on id
    @GetMapping("/subject/{id}")
    ResponseEntity<SubjectDTO> getSubjectById(@PathVariable long id){
        SubjectDTO sub = subServ.findByPk(id);
        return new ResponseEntity<>(sub, HttpStatus.OK);
    }
    
    //search subject in pages
    @GetMapping("/subject/searchByPages/{name}/{pageNo}/{pageSize}")
	List<SubjectDTO> search(@PathVariable String name,@RequestParam(value = "page", defaultValue = "0") int pageNo,
			@RequestParam(value = "size", defaultValue = "10") int pageSize){
		return subServ.search(name, pageNo, pageSize);
	}
	
    //search subject
	@GetMapping("/subject/search/{name}")
	List<SubjectDTO> search(@PathVariable String name){
		return subServ.search(name);
	}
}
