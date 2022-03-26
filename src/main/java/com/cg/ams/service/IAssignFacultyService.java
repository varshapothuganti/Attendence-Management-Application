package com.cg.ams.service;

import java.util.List;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;

public interface IAssignFacultyService {

	public long add(AssignFacultyEntity entity) throws DuplicateRecordException;
	public void update(AssignFacultyEntity entity) throws RecordNotFoundException;
	public void delete(AssignFacultyEntity entity) throws RecordNotFoundException;
	public AssignFacultyEntity findByName(String name) throws RecordNotFoundException;
	public AssignFacultyEntity findByPk(long id) throws RecordNotFoundException;
	public List<AssignFacultyEntity> search(AssignFacultyEntity entity, long pageNo, int pageSize);
	public List<AssignFacultyEntity> search(AssignFacultyEntity entity);
	
}