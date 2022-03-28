package com.cg.ams.service;

import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class SubjectServiceImpl implements com.cg.ams.service.SubjectService {


    @Autowired
    ISubjectRepository subRepo;

    @Override
    public long add(SubjectEntity entity) {
        subRepo.save(entity);
        return entity.getId();
    }

    @Override
    public void update(SubjectEntity entity) throws RecordNotFoundException {
        Optional<SubjectEntity> sub1 = subRepo.findById(entity.getId());
        if (!sub1.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+entity.getId());
        }
        SubjectEntity sub = sub1.get();
        sub.setCourse(entity.getCourse());
        sub.setName(entity.getName());
        sub.setSemester(entity.getSemester());
        sub.setSubjectCode(entity.getSubjectCode());
        subRepo.save(sub);
    }

    @Override
    public void delete(SubjectEntity entity) throws RecordNotFoundException {
        Optional<SubjectEntity> sub1 = subRepo.findById(entity.getId());
        if (!sub1.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+entity.getId());
        }
        subRepo.delete(entity);
    }

    @Override
    public SubjectEntity findByName(String name) throws Exception {
        try {
            Optional<SubjectEntity> sub1 = subRepo.findByName(name);
            if (!sub1.isPresent()) {
                throw new RecordNotFoundException("Subject not found with the name: "+name);
            }
            return sub1.get();
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new DuplicateRecordException("Duplicate Subjects found with the given name: " + name);
        }
    }

    @Override
    public SubjectEntity findByPk(long id) throws RecordNotFoundException {
        Optional<SubjectEntity> sub1 = subRepo.findById(id);
        if (!sub1.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+id);
        }
        return sub1.get();
    }

    @Override
    public List<SubjectEntity> getAllSubjects() {
        return subRepo.findAll();
    }

    @Override
    public List<SubjectEntity> search(SubjectEntity entity, int pageNo, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNo, pageSize);
		return subRepo.findByNameIgnoreCase(entity.getName(), pageable);
    }

    @Override
    public List<SubjectEntity> search(SubjectEntity entity) {
    	return subRepo.findByNameIgnoreCase(entity.getName());
    }


}
