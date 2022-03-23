package com.example.demo.com.cg.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.cg.ams.entity.SubjectEntity;
import com.example.demo.com.cg.ams.exception.RecordNotFoundException;
@Service
public interface SubjectService {
	

	public long add(SubjectEntity entity);
	public void update(SubjectEntity entity) throws RecordNotFoundException;
	public void delete(SubjectEntity entity) throws RecordNotFoundException;
	public SubjectEntity findByName(String name) throws Exception;
	public SubjectEntity findByPk(long id) throws RecordNotFoundException;
	public List<SubjectEntity> search(SubjectEntity entity, long pageNo, int pageSize);
	public List<SubjectEntity> search(SubjectEntity entity);
	public List<SubjectEntity> getAllSubjects();
	
	
}
