package com.SimpleEventMaster.awesomeAPP.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;




@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = Long.class)
public class UserDTO {

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
}
