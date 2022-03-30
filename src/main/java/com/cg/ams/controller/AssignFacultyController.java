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

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;
import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.service.IAssignFacultyService;

@RestController
public class AssignFacultyController {

	@Autowired
	IAssignFacultyService afServ;

	@PostMapping("/faculty/add")
	ResponseEntity<Long> add(@Valid @RequestBody AssignFacultyInputDTO afInDTO) {
		long l = afServ.add(afInDTO);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@PostMapping("/faculty/update")
	ResponseEntity<AssignFacultyOutputDTO> update(@Valid @RequestBody AssignFacultyInputDTO afInDTO) {
		afServ.update(afInDTO);
		AssignFacultyEntity afe = new AssignFacultyEntity(afInDTO);
		AssignFacultyOutputDTO afOutDTO = new AssignFacultyOutputDTO(afe);
		return new ResponseEntity<>(afOutDTO, HttpStatus.OK);
	}

	@DeleteMapping("/faculty/delete")
	ResponseEntity<String> delete(@Valid @RequestBody AssignFacultyInputDTO afInDTO) {
		afServ.delete(afInDTO);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/faculty/getByName/{name}")
	ResponseEntity<AssignFacultyOutputDTO> findByName(@PathVariable("name") String name) {
		AssignFacultyOutputDTO afOutDTO = afServ.findByName(name);
		return new ResponseEntity<>(afOutDTO, HttpStatus.OK);
	}

	@GetMapping("/faculty/getByPk/{id}")
	ResponseEntity<AssignFacultyOutputDTO> findByPk(@PathVariable("id") long id) {
		AssignFacultyOutputDTO afOutDTO = afServ.findByPk(id);
		return new ResponseEntity<>(afOutDTO, HttpStatus.OK);
	}

	@GetMapping("/faculty/searchByPages/{pageNo}/{pageSize}")
	List<AssignFacultyOutputDTO> search(@RequestBody AssignFacultyInputDTO afInDTO, @PathVariable("pageNo") long pageNo,
			@PathVariable("pageSize") int pageSize) {
		return afServ.search(afInDTO, pageNo, pageSize);
	}

	@GetMapping("/faculty/search")
	List<AssignFacultyOutputDTO> search(@RequestBody AssignFacultyInputDTO afInDTO) {
		return afServ.search(afInDTO);
	}

}