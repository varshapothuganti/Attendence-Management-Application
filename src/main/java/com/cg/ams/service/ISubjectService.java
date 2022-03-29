package com.cg.ams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.entity.SubjectEntity;

@Service
public interface ISubjectService {

	public long add(SubjectDTO subdto);

	public void update(SubjectDTO subdto);

	public void delete(SubjectDTO subdto);

	public SubjectDTO findByName(String name);

	public SubjectDTO findByPk(long id);

	public List<SubjectDTO> search(String name, int pageNo, int pageSize);

	public List<SubjectDTO> search(String name);

}
