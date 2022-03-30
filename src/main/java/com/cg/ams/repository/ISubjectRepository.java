package com.cg.ams.repository;

import com.cg.ams.dto.SubjectDTO;
import com.cg.ams.entity.SubjectEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ISubjectRepository extends JpaRepository<SubjectEntity, Long> {

	public Optional<SubjectEntity> findById(long id);
    public Optional<SubjectEntity> findByName(String name);
    public List<SubjectDTO> findByNameIgnoreCase(String name,Pageable pageable);
	public List<SubjectDTO> findByNameIgnoreCase(String name);
	
	@Query(value= "delete from subject where subject.id=:subject_id  delete from faculty_subjects where faculty_subjects.subject_id=:subject_id", nativeQuery = true)
	public void delete(@Param("subject_id") long l);


}