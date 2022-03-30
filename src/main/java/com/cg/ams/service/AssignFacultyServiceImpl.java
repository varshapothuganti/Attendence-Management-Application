package com.cg.ams.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.ams.dto.AssignFacultyInputDTO;
import com.cg.ams.dto.AssignFacultyOutputDTO;
import com.cg.ams.entity.AssignFacultyEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.IAssignFacultyRepository;

@Service
public class AssignFacultyServiceImpl implements IAssignFacultyService {

	@Autowired
	IAssignFacultyRepository afRep;

	private String message = "Cannot find faculty record with id: ";
	
	@Override
	public long add(AssignFacultyInputDTO afInDTO) {
		AssignFacultyEntity entity = new AssignFacultyEntity(afInDTO);
		Optional<AssignFacultyEntity> afe = afRep.findById(entity.getId());
		if (afe.isPresent()) {
			throw new DuplicateRecordException("The faculty with id: " + entity.getId() + " already exists!");
		}
		afRep.save(entity);
		return entity.getId();
	}

	@Override
	public void update(AssignFacultyInputDTO afInDTO) {
		AssignFacultyEntity entity = new AssignFacultyEntity(afInDTO);
		Optional<AssignFacultyEntity> afe = afRep.findById(entity.getId());
		if (!afe.isPresent()) {
			throw new RecordNotFoundException(message + entity.getId());
		}
		afRep.save(entity);
	}

	@Override
	public void delete(AssignFacultyInputDTO afInDTO) {
		AssignFacultyEntity entity = new AssignFacultyEntity(afInDTO);
		AssignFacultyEntity afe = afRep.findById(entity.getId()).orElseThrow(
				() -> new RecordNotFoundException(message + entity.getId()));

		afRep.deleteById(afe.getId());
	}

	@Override
	public AssignFacultyOutputDTO findByName(String name) {
		Optional<AssignFacultyEntity> afe = Optional.ofNullable(afRep.findByUserName(name));
		if (!afe.isPresent()) {
			throw new RecordNotFoundException("The faculty with userName: "+name+" does not exist!");
		}
		return new AssignFacultyOutputDTO(afe.get());
	}

	@Override
	public AssignFacultyOutputDTO findByPk(long id) {
		Optional<AssignFacultyEntity> afe = afRep.findById(id);
		if (!afe.isPresent()) {
			throw new RecordNotFoundException(message + id);
		}
		return new AssignFacultyOutputDTO(afe.get());
	}

	@Override
	public List<AssignFacultyOutputDTO> search(AssignFacultyInputDTO afInDTO, long pageNo, int pageSize) {
		Pageable pageable = PageRequest.of((int) pageNo, pageSize);
		AssignFacultyEntity entity = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> al = afRep.findByUserNameIgnoreCase(entity.getUserName(), pageable);
		List<AssignFacultyOutputDTO> al1 = new ArrayList<>();
		for(AssignFacultyEntity afe : al) {
			al1.add(new AssignFacultyOutputDTO(afe));
		}
		return al1;
	}

	@Override
	public List<AssignFacultyOutputDTO> search(AssignFacultyInputDTO afInDTO) {
		AssignFacultyEntity entity = new AssignFacultyEntity(afInDTO);
		List<AssignFacultyEntity> al = afRep.findByUserNameIgnoreCase(entity.getUserName());
		List<AssignFacultyOutputDTO> al1 = new ArrayList<>();
		for(AssignFacultyEntity afe : al) {
			al1.add(new AssignFacultyOutputDTO(afe));
		}
		return al1;
	}

}