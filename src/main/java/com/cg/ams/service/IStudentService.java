package com.cg.ams.service;

import java.util.List;

import com.cg.ams.entity.StudentEntity;

public interface IStudentService {
	
	public long add(StudentEntity entity);
	
	public void update(StudentEntity entity);
	
	public void delete(StudentEntity entity);
	
	public List<StudentEntity> findByName(String name);
	
	public StudentEntity findByPk(long id);
	
	public List<StudentEntity> search(String name, int pageNo, int pageSize);

	public List<StudentEntity> search(String name);

}
