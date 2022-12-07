package com.SimpleEventMaster.awesomeAPP.modelEntity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private User user;

}
