package com.SimpleEventMaster.awesomeAPP.controller;


import com.SimpleEventMaster.awesomeAPP.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping("/make-admin/{userId}")
    public void giveUserRoleAdmin(@PathVariable("userId") long id){
        roleService.giveUserRoleAdmin(id);
    }
    @PutMapping("/make-user/{userId}")
    public void giveUserRoleUser(@PathVariable("userId") long id){
        roleService.giveUserRoleUser(id);
    }
}
