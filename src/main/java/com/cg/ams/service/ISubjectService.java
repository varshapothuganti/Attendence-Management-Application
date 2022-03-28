package com.cg.ams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ams.entity.SubjectEntity;

@Service
public interface ISubjectService {

	public long add(SubjectEntity entity);

	public void update(SubjectEntity entity);

	public void delete(SubjectEntity entity);

	public SubjectEntity findByName(String name);

	public SubjectEntity findByPk(long id);

	public List<SubjectEntity> search(SubjectEntity entity);

	public List<SubjectEntity> getAllSubjects();

	List<SubjectEntity> search(SubjectEntity entity, int pageNo, int pageSize);

}
