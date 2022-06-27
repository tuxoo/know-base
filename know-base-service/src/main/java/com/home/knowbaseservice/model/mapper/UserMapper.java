package com.home.knowbaseservice.model.mapper;

import com.home.knowbaseservice.model.dto.UserDTO;
import com.home.knowbaseservice.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    UserDTO toDTO(User entity);
}
