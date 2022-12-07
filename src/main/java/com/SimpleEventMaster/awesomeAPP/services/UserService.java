package com.SimpleEventMaster.awesomeAPP.services;


import com.SimpleEventMaster.awesomeAPP.dto.UserDTO;
import com.SimpleEventMaster.awesomeAPP.mapper.UserMapper;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    private RoleService roleService;


    public UserService(UserRepository userRepository, UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    public List<UserDTO> findAll(){
        List<User>users=userRepository.findAll();
        List<UserDTO>userDTOS=userMapper.mapToDTO(users);
        return userDTOS;
    }
    public UserDTO findById(long id){
        User user=userRepository.findById(id).orElse(null);
        UserDTO userDTO=userMapper.mapToDTO(user);
        return userDTO;

    }
    public UserDTO addOrUpdate(UserDTO theUserDTO){
        User user=userMapper.mapToUser(theUserDTO);
        user.setPassword(new BCryptPasswordEncoder().encode(theUserDTO.getPassword()));
        User user2=userRepository.save(user);
        roleService.giveUserRoleUser(user2.getId());//give admin or user role
         return userMapper.mapToDTO(user2);
    }

    public void delete(long id){
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e){
            e.getMessage();
            throw new RuntimeException("User id "+id+" doesnt exist");
        }
    }

    public void approveUser(long id){
        User user=userRepository.findById(id).get();
        user.setEnabled(true);
        userRepository.save(user);

    }
    public void disApproveUser(long id){
        User user=userRepository.findById(id).get();
        user.setEnabled(false);
        userRepository.save(user);

    }



}
