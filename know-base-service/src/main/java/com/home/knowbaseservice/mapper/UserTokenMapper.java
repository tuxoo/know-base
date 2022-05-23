package com.home.knowbaseservice.mapper;

import com.home.knowbaseservice.dto.UserTokenDTO;
import com.home.knowbaseservice.entity.UserToken;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTokenMapper {

    UserTokenDTO toDTO(UserToken userToken);
}
