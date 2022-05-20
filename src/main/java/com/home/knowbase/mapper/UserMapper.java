package com.home.knowbase.mapper;

import com.home.knowbase.dto.UserDTO;
import com.home.knowbase.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {

    UserDTO toDTO(User entity);
}
