package com.cg.ams.service;

import java.util.List;



import com.cg.ams.entity.StudentEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

public interface IStudentService {
	
	public long add(StudentEntity entity) throws DuplicateRecordException;
	
	public void update(StudentEntity entity) throws Exception;
	
	public void delete(StudentEntity entity) throws Exception;
	
	public List<StudentEntity> findByName(String name) throws  RecordNotFoundException;
	
	public StudentEntity findByPk(long id) throws Exception;
	
	public List<StudentEntity> search(String name, int pageNo, int pageSize) throws RecordNotFoundException, Exception;

	public List<StudentEntity> search(String name) throws RecordNotFoundException, Exception;


}
