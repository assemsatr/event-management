package com.SimpleEventMaster.awesomeAPP.repositoryDAO;

import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    //optional is special object, provides null or an object
    Optional<User>findByEmail(String email);

    @Query("select u from User u where u.email =:email")
    Optional<User>findByEmailHQL(String email);

    @Query(value = "select * from users where username=:email",nativeQuery = true)
    Optional<User>findByEmailQNativeQuery(String email);





}
