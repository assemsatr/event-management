package com.SimpleEventMaster.awesomeAPP.controller;


import com.SimpleEventMaster.awesomeAPP.dto.UserDTO;
import com.SimpleEventMaster.awesomeAPP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/findAll",method = RequestMethod.GET,produces ={MimeTypeUtils.APPLICATION_JSON_VALUE} )
    public List<UserDTO> findAll(){
        return userService.findAll();

    }
    @GetMapping("/findById/{userId}")
    public UserDTO findById(@PathVariable("userId") long id){
        return userService.findById(id);

    }
    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public UserDTO addOrUpdate(@RequestBody UserDTO theUser){
        return userService.addOrUpdate(theUser);
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") long id){
        userService.delete(id);
    }


    @PutMapping("/approve/{userId}")
    public void approveUser(@PathVariable("userId") long id){
        userService.approveUser(id);
    }
    @PutMapping("/disApprove/{userId}")
    public void disApproveUser(@PathVariable("userId") long id){
        userService.disApproveUser(id);
    }


    public void nothingToDoMethod(){
        int result=5*6;
        System.out.println(result);
    }
}
