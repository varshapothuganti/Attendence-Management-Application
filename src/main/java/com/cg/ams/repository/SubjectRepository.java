package com.cg.ams.repository;

import com.cg.ams.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

    public Optional<SubjectEntity> findById(long id);

    public Optional<SubjectEntity> findByName(String name);


}
