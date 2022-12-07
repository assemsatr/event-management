package com.SimpleEventMaster.awesomeAPP.repositoryDAO;

import com.SimpleEventMaster.awesomeAPP.modelEntity.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant,Long> {
}
