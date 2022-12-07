package com.SimpleEventMaster.awesomeAPP.repositoryDAO;

import com.SimpleEventMaster.awesomeAPP.modelEntity.Event;
import com.SimpleEventMaster.awesomeAPP.modelEntity.EventStatus;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<Event> findByCreatorEmail(String creator);

    @Query("select u from Event u where u.creator=:creator")
    Optional<Event>findByCreatorEmailHQL(String creator);

    @Query(value = "select e.* from event e join user_table ut on ut.id where ut.username=:email",nativeQuery = true)
    Optional<User>findByCreatorQNativeQuery(String email);


    //for filter
    List<Event> findByStartDateAfterAndStatus(LocalDate localDate, EventStatus status);


}
