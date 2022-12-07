package com.SimpleEventMaster.awesomeAPP.repositoryDAO;

import com.SimpleEventMaster.awesomeAPP.modelEntity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleUser,Long> {
}
