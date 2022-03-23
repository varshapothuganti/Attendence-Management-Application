package com.example.demo.com.cg.ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.com.cg.ams.entity.SubjectEntity;


@Repository
public interface SubjectDAO extends JpaRepository<SubjectEntity, Integer> {
	
	public Optional<SubjectEntity> findById(long id);
	public Optional<SubjectEntity> findByName(String name);

	//public default long add(SubjectEntity entity) {
		//this.save(entity);
		//return entity.getId();
	//}
	//public void update(SubjectEntity entity);
	//public void delete(SubjectEntity entity);
	
	//public default SubjectEntity findByPk(long id) {
		//return null;
		
	//}
	//public List<SubjectEntity> search(SubjectEntity entity, long pageNo, int pageSize);
	//public List<SubjectEntity> search(SubjectEntity entity);
	
	
}
