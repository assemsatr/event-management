package com.SimpleEventMaster.awesomeAPP.mapper;

import com.SimpleEventMaster.awesomeAPP.dto.UserDTO;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapToDTO(User user);
    List<UserDTO> mapToDTO(List<User> users);
    User mapToUser(UserDTO userDTO);
    List<User>user(List<UserDTO>userDTOS);
}
