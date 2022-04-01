package com.cg.ams.service;

import java.util.List;

import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;


/**
 * Defines all the operation that are supported by this service
 *
 * @author varsha
 */
public interface IStudentService {
	
	


	public long add(StudentInputDTO stdInDTO);

	public void update(StudentInputDTO stdDTO);

	public void delete(StudentInputDTO stdDTO);

	public List<StudentOutputDTO> findByName(String name);

	public StudentOutputDTO findByPk(long id);

	public List<StudentOutputDTO> search(String name, int pageNo, int pageSize);

	List<StudentOutputDTO> search(String name);

}
