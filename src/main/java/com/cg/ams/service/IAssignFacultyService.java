package com.cg.ams.service;

import java.util.List;

import com.cg.ams.entity.AssignFacultyEntity;

public interface IAssignFacultyService {

	public long add(AssignFacultyEntity entity);

	public void update(AssignFacultyEntity entity);

	public void delete(AssignFacultyEntity entity);

	public AssignFacultyEntity findByName(String name);

	public AssignFacultyEntity findByPk(long id);

	public List<AssignFacultyEntity> search(AssignFacultyEntity entity, long pageNo, int pageSize);

	public List<AssignFacultyEntity> search(AssignFacultyEntity entity);

}