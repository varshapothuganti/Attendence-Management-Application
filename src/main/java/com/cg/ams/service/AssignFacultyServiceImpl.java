package com.cg.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.AssignFacultyRepository;

@Service
public class AssignFacultyServiceImpl implements IAssignFacultyService {

	@Autowired
	AssignFacultyRepository afRep;

	@Override
	public long add(AssignFacultyEntity entity) {
		Optional<AssignFacultyEntity> afe = afRep.findById(entity.getId());
		if (afe.isPresent()) {
			throw new DuplicateRecordException("The entity with id: " + entity.getId() + " already exists!");
		}
		afRep.save(entity);
		return entity.getId();
	}

	@Override
	public void update(AssignFacultyEntity entity) {
		Optional<AssignFacultyEntity> afe = afRep.findById(entity.getId());
		if (!afe.isPresent()) {
			throw new RecordNotFoundException("The faculty record with id: " + entity.getId() + " is not found!");
		}
		afRep.save(entity);
	}

	@Override
	public void delete(AssignFacultyEntity entity) {
		Optional<AssignFacultyEntity> assignFaculty = afRep.findById(entity.getId());
		if (assignFaculty.isEmpty())
			throw new RecordNotFoundException("The faculty record with id: " + entity.getId() + " is not found!");

		afRep.deleteById(entity.getId());
	}

	@Override
	public AssignFacultyEntity findByName(String name) {
		Optional<AssignFacultyEntity> afe = Optional.ofNullable(afRep.findByUserName(name));
		if (!afe.isPresent()) {
			throw new RecordNotFoundException("The faculty record with name: " + name + " is not found!");
		}
		return afe.get();
	}

	@Override
	public AssignFacultyEntity findByPk(long id) {
		Optional<AssignFacultyEntity> afe = afRep.findById(id);
		if (!afe.isPresent()) {
			throw new RecordNotFoundException("The faculty record with id: " + id + " is not found!");
		}
		return afe.get();
	}

	@Override
	public List<AssignFacultyEntity> search(AssignFacultyEntity entity, long pageNo, int pageSize) {
		Pageable pageable = PageRequest.of((int) pageNo, pageSize);
		return afRep.findByUserNameIgnoreCase(entity.getUserName(), pageable);
	}

	@Override
	public List<AssignFacultyEntity> search(AssignFacultyEntity entity) {
		return afRep.findByUserNameIgnoreCase(entity.getUserName());
	}

}