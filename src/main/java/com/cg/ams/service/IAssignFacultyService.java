package com.cg.ams.service;

import java.util.List;

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;

/*
 * An Interface from which a service class can be implemented for total abstraction.
 * @Author Ramu
 */

public interface IAssignFacultyService {

	public long add(AssignFacultyInputDTO afDTO);

	public void update(AssignFacultyInputDTO afDTO);

	public void delete(AssignFacultyInputDTO afDTO);

	public AssignFacultyOutputDTO findByName(String name);

	public AssignFacultyOutputDTO findByPk(long id);

	public List<AssignFacultyOutputDTO> search(AssignFacultyInputDTO afDTO, long pageNo, int pageSize);

	public List<AssignFacultyOutputDTO> search(AssignFacultyInputDTO afDTO);

}