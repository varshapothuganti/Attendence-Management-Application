package com.cg.ams.repository;

import com.cg.ams.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

}
