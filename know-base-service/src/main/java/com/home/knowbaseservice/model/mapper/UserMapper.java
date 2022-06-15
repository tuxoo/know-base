package com.home.knowbaseservice.model.mapper;

import com.home.knowbaseservice.model.entity.User;
import com.home.knowbaseservice.model.entity.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    UserDTO toDTO(User entity);
}
