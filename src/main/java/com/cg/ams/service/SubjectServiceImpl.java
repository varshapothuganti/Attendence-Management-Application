package com.cg.ams.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.DuplicateRecordException;
import com.cg.ams.exception.RecordNotFoundException;
import com.cg.ams.repository.ISubjectRepository;


/**
 * Implements ISubjectService
 * @author ADMIN
 *
 */

@Service
public class SubjectServiceImpl implements ISubjectService {


    @Autowired
    ISubjectRepository subRepo;

    /**
     * adds a subject to the database 
     * @param SubjectEntity
     * @return subjectId
     */
    
    @Override
    public long add(SubjectDTO subdto) {
    	SubjectEntity entity=new SubjectEntity(subdto);
        subRepo.save(entity);
        return entity.getId();
    }

    /**
     * updates subject in the database and throws exception if it does not exist in the database
     * @param SubjectEntity
     */
    
    @Override
    public void update(SubjectDTO subdto) {
    	SubjectEntity entity=new SubjectEntity(subdto);
        Optional<SubjectEntity> sub1 = subRepo.findById(subdto.getId());
        if (!sub1.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+entity.getId());
        }
        subRepo.save(entity);
    }
    

    /**
     * deletes subject in the database and throws exception if it does not exist in the database
     * @param SubjectEntity
     */

    @Override
    public void delete(SubjectDTO subdto) {
    	SubjectEntity entity=new SubjectEntity(subdto);
        Optional<SubjectEntity> sub1 = subRepo.findById(subdto.getId());
        if (!sub1.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+entity.getId());
        }
        subRepo.delete(entity);
    }

    /**
     * finds subject by name and throws exception if it does not exist in the database
     * @param name
     * @return SubjectEntity
     */
    
    @Override
    public SubjectDTO findByName(String name) {
        try {
            Optional<SubjectEntity> entity = subRepo.findByName(name);
            if (!entity.isPresent()) {
                throw new RecordNotFoundException("Subject not found with the name: "+name);
            }
            SubjectDTO subdto=new SubjectDTO(entity.get());
            return subdto;
            } 
            catch (IncorrectResultSizeDataAccessException e) {
                throw new DuplicateRecordException("Duplicate Subjects found with the given name: " + name);
            }
    }

    /**
     * finds subject by id and throws exception if it does not exist in the database
     * @param subjectId
     * @return SubjectEntity
     */
    
    @Override
    public SubjectDTO findByPk(long id) throws RecordNotFoundException {
        Optional<SubjectEntity> entity = subRepo.findById(id);
        
        if (!entity.isPresent()) {
            throw new RecordNotFoundException("Subject not found with the id: "+id);
        }
        SubjectDTO subdto=new SubjectDTO(entity.get());
        return subdto;
    }


    /**
     * searches database and return results after paginating
     * @param subjectentity
     * @param pageNo
     * @param pageSize
     * @return List<SubjectEntity>
     */
    
    @Override
    public List<SubjectDTO> search(String name, int pageNo, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNo, pageSize);
		return subRepo.findByNameIgnoreCase(name, pageable);
		
    }

    /**
     * fetches database based on given entity
     * @param subjectentity
     * @return List<SubjectEntity
     */
    
    @Override
    public List<SubjectDTO> search(String name) {
    	return subRepo.findByNameIgnoreCase(name);
    }

}
