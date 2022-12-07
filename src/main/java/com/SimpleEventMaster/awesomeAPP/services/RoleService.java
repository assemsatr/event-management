package com.SimpleEventMaster.awesomeAPP.services;


import com.SimpleEventMaster.awesomeAPP.modelEntity.RoleUser;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.RoleRepository;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void giveUserRoleAdmin(long id){
        User user=userRepository.findById(id).get();
        RoleUser roleUser=new RoleUser();
        roleUser.setUser(user);
        roleUser.setName("ROLE_ADMIN");
        roleRepository.save(roleUser);




    }
    public void giveUserRoleUser(long id){
        User user=userRepository.findById(id).get();
        RoleUser roleUser=new RoleUser();
        roleUser.setUser(user);
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

    }
}
