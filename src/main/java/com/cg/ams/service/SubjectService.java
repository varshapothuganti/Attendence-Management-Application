package com.cg.ams.service;

import com.cg.ams.entity.SubjectEntity;
import com.cg.ams.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface SubjectService {


    public long add(SubjectEntity entity);

    public void update(SubjectEntity entity) throws RecordNotFoundException;

    public void delete(SubjectEntity entity) throws RecordNotFoundException;

    public SubjectEntity findByName(String name) throws Exception;

    public SubjectEntity findByPk(long id) throws RecordNotFoundException;

    public List<SubjectEntity> search(SubjectEntity entity);

    public List<SubjectEntity> getAllSubjects();

	List<SubjectEntity> search(SubjectEntity entity, int pageNo, int pageSize);


}
