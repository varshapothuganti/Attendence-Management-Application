package com.cg.ams.service;

import java.util.List;


import com.cg.ams.dto.StudentInputDTO;
import com.cg.ams.dto.StudentOutputDTO;
import com.cg.ams.entity.StudentEntity;

/**
 * Defines all the operation that are supported by this service
 *
 * @author varsha
 */
public interface IStudentService {
	
	
	public List<StudentOutputDTO> findByName(String name);
	
	public StudentOutputDTO findByPk(long id);



	List<StudentOutputDTO> search(String name);

	public long add(StudentInputDTO stdDTO);

	public void update(StudentInputDTO stdDTO);

	public void delete(StudentInputDTO stdDTO);



	public List<StudentOutputDTO> search(String name, int pageNo, int pageSize);

	public long add(StudentEntity std);



}
