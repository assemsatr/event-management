package com.SimpleEventMaster.awesomeAPP.modelEntity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username",nullable = false,length = 64)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,length = 20)
    private String firstName;
    @Column(nullable = false,length = 20)
    private String lastName;
    private boolean enabled=false;


    @ManyToMany
    @JoinTable(
            name = "event_participant",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> participants;


}
