package com.example.demo.com.cg.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.com.cg.ams.entity.SubjectEntity;
import com.example.demo.com.cg.ams.exception.RecordNotFoundException;
import com.example.demo.com.cg.ams.repository.SubjectDAO;


@Service
public class SubjectServiceImpl implements SubjectService {
	

	@Autowired
	SubjectDAO subRepo;

	@Override
	public long add(SubjectEntity entity) {
		subRepo.save(entity);
		return entity.getId();
	}
	
	@Override
	public void update(SubjectEntity entity) throws RecordNotFoundException{
		Optional<SubjectEntity> sub1=subRepo.findById(entity.getId());
		if(!sub1.isPresent()) {
			throw new RecordNotFoundException("Subject not found with the given object");
		}
		SubjectEntity sub=sub1.get();
		sub.setCourse(entity.getCourse());
		sub.setName(entity.getName());
		sub.setSemester(entity.getSemester());
		sub.setSubjectCode(entity.getSubjectCode());
		subRepo.save(sub);
	}
	
	@Override
	public void delete(SubjectEntity entity) throws RecordNotFoundException {
		Optional<SubjectEntity> sub1=subRepo.findById(entity.getId());
		if(!sub1.isPresent()) {
			throw new RecordNotFoundException("Subject not found with the given object");
		}
		subRepo.delete(entity);
	}
	
	@Override
	public SubjectEntity findByName(String name) throws Exception {
		
		try {
			Optional<SubjectEntity> sub1=subRepo.findByName(name);
			if(!sub1.isPresent()) {
				throw new RecordNotFoundException("Subject not found with the given object");
			}
			return sub1.get();
			}
			catch(IncorrectResultSizeDataAccessException e) {
				throw new RecordNotFoundException("Duplicate Subjects found with the given name: "+ name);
			}
	}
	
	@Override
	public SubjectEntity findByPk(long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<SubjectEntity> sub1=subRepo.findById(id);
		if(!sub1.isPresent()) {
			throw new RecordNotFoundException("Subject not found with the given id");
		}
		return sub1.get();
	}
	
	@Override
	public List<SubjectEntity> getAllSubjects() {
		// TODO Auto-generated method stub
		return subRepo.findAll();
	}

	@Override
	public List<SubjectEntity> search(SubjectEntity entity, long pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectEntity> search(SubjectEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
